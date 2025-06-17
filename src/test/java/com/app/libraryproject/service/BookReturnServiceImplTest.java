package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import com.app.libraryproject.dto.bookreturn.CreateBookReturnRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.entity.MemberPayment;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.MemberPaymentType;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.MemberPaymentRepository;
import com.app.libraryproject.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookReturnServiceImplTest {

    @Mock
    private BookLoanRepository bookLoanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberPaymentRepository memberPaymentRepository;

    @Mock
    private BookReservationService bookReservationService;

    @Mock
    private DisposedBookService disposedBookService;

    @InjectMocks
    private BookReturnServiceImpl bookReturnService;

    private Member member;
    private Book book;
    private BookLoan bookLoan;

    @BeforeEach
    void setUp() {
        member = Member.builder().id(1L).name("Jan").surname("Kowalski").build();
        book = Book.builder().id(1L).title("Władca Pierścieni").quantity(1).build();
        bookLoan = BookLoan.builder()
                .id(1L)
                .member(member)
                .book(book)
                .loanDate(LocalDate.now().minusDays(10)) // On-time return
                .build();
    }

    @Test
    void returnBook_OnTime_ShouldSucceed() {
        // Given
        CreateBookReturnRequest request = new CreateBookReturnRequest(1L, 1L, false);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookLoanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.of(bookLoan));

        // When
        BookReturnResponse response = bookReturnService.returnBook(request);

        // Then
        assertThat(response.isLate()).isFalse();
        assertThat(response.lateFee()).isZero();
        assertThat(response.isDamaged()).isFalse();
        assertThat(response.damageFee()).isZero();
        assertThat(response.message()).isEqualTo("Książka została zwrócona. Zwrot przebiegł pomyślnie.");

        verify(bookLoanRepository, times(1)).delete(bookLoan);
        verify(bookReservationService, times(1)).processNextReservationForBook(book);
        verify(disposedBookService, never()).addDisposedBook(any(Book.class));
        verify(memberPaymentRepository, never()).save(any(MemberPayment.class));
    }

    @Test
    void returnBook_Late_ShouldChargeFee() {
        // Given
        bookLoan.setLoanDate(LocalDate.now().minusDays(20)); // Late return
        CreateBookReturnRequest request = new CreateBookReturnRequest(1L, 1L, false);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookLoanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.of(bookLoan));

        // When
        BookReturnResponse response = bookReturnService.returnBook(request);

        // Then
        assertThat(response.isLate()).isTrue();
        assertThat(response.lateFee()).isGreaterThan(0);
        assertThat(response.isDamaged()).isFalse();
        assertThat(response.damageFee()).isZero();
        assertThat(response.message()).contains("Naliczono opłatę za spóźnienie");

        verify(memberPaymentRepository, times(1)).save(argThat(payment ->
                payment.getMemberPaymentType() == MemberPaymentType.LATE_RETURN_FEE &&
                        payment.getAmount().compareTo(BigDecimal.ZERO) > 0
        ));
        verify(bookLoanRepository, times(1)).delete(bookLoan);
        verify(bookReservationService, times(1)).processNextReservationForBook(book);
        verify(disposedBookService, never()).addDisposedBook(any(Book.class));
    }

    @Test
    void returnBook_Damaged_ShouldChargeFeeAndDispose() {
        // Given
        CreateBookReturnRequest request = new CreateBookReturnRequest(1L, 1L, true); // Damaged book

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookLoanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.of(bookLoan));

        // When
        BookReturnResponse response = bookReturnService.returnBook(request);

        // Then
        assertThat(response.isDamaged()).isTrue();
        assertThat(response.damageFee()).isEqualTo(25.0);
        assertThat(response.isLate()).isFalse();
        assertThat(response.lateFee()).isZero();
        assertThat(response.message()).contains("Zarejestrowano uszkodzenie książki i przeznaczono ją do utylizacji.");

        verify(memberPaymentRepository, times(1)).save(argThat(payment ->
                payment.getMemberPaymentType() == MemberPaymentType.DAMAGE_FEE &&
                        payment.getAmount().doubleValue() == 25.0
        ));
        verify(disposedBookService, times(1)).addDisposedBook(book);
        verify(bookReservationService, never()).processNextReservationForBook(any(Book.class));
        verify(bookLoanRepository, times(1)).delete(bookLoan);
    }

    @Test
    void returnBook_LateAndDamaged_ShouldHandleBoth() {
        // Given
        bookLoan.setLoanDate(LocalDate.now().minusDays(20)); // Late return
        CreateBookReturnRequest request = new CreateBookReturnRequest(1L, 1L, true); // Damaged book

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookLoanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.of(bookLoan));

        // When
        BookReturnResponse response = bookReturnService.returnBook(request);

        // Then
        assertThat(response.isLate()).isTrue();
        assertThat(response.lateFee()).isGreaterThan(0);
        assertThat(response.isDamaged()).isTrue();
        assertThat(response.damageFee()).isEqualTo(25.0);
        assertThat(response.message()).contains("Naliczono opłatę za spóźnienie").contains("Zarejestrowano uszkodzenie książki");

        verify(memberPaymentRepository, times(2)).save(any(MemberPayment.class));
        verify(disposedBookService, times(1)).addDisposedBook(book);
        verify(bookReservationService, never()).processNextReservationForBook(any(Book.class));
        verify(bookLoanRepository, times(1)).delete(bookLoan);
    }

    @Test
    void returnBook_MemberNotFound_ShouldThrowException() {
        // Given
        CreateBookReturnRequest request = new CreateBookReturnRequest(99L, 1L, false);
        when(memberRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                bookReturnService.returnBook(request)
        );
        assertThat(exception.getMessage()).isEqualTo("Member not found with id: 99");
    }

    @Test
    void returnBook_BookNotFound_ShouldThrowException() {
        // Given
        CreateBookReturnRequest request = new CreateBookReturnRequest(1L, 99L, false);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                bookReturnService.returnBook(request)
        );
        assertThat(exception.getMessage()).isEqualTo("Book not found with id: 99");
    }

    @Test
    void returnBook_LoanNotFound_ShouldThrowException() {
        // Given
        CreateBookReturnRequest request = new CreateBookReturnRequest(1L, 1L, false);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookLoanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                bookReturnService.returnBook(request)
        );
        assertThat(exception.getMessage()).isEqualTo("Book loan not found for this member and book.");
    }
}