package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreservation.CreateReservationRequest;
import com.app.libraryproject.dto.bookreservation.ReservationResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.entity.BookReservation;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.RecordConflictException;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.model.ReservationStatus;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.BookReservationRepository;
import com.app.libraryproject.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookReservationServiceImplTest {

    @Mock
    private BookReservationRepository reservationRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private BookLoanRepository loanRepository;

    @InjectMocks
    private BookReservationServiceImpl reservationService;

    private Book book;
    private Member member;
    private BookReservation reservation;

    @BeforeEach
    void setUp() {
        book = Book.builder().id(1L).title("Test Book").quantity(0).build();
        member = Member.builder().id(1L).name("Test").surname("User").build();
        reservation = BookReservation.builder()
                .id(1L)
                .book(book)
                .member(member)
                .status(ReservationStatus.ACTIVE)
                .reservationDate(LocalDate.now())
                .build();
    }

    @Test
    void createReservation_Success() {
        // Given
        CreateReservationRequest request = new CreateReservationRequest(1L, 1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(loanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.empty());
        when(reservationRepository.findByBookIdAndMemberIdAndStatusIn(anyLong(), anyLong(), anyList()))
                .thenReturn(Optional.empty());
        when(reservationRepository.save(any(BookReservation.class))).thenReturn(reservation);

        // When
        ReservationResponse response = reservationService.createReservation(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.book().id()).isEqualTo(book.getId());
        assertThat(response.member().id()).isEqualTo(member.getId());
        assertThat(response.status()).isEqualTo(ReservationStatus.ACTIVE);
        verify(reservationRepository, times(1)).save(any(BookReservation.class));
    }

    @Test
    void createReservation_BookIsAvailable_ShouldThrowConflict() {
        // Given
        book.setQuantity(1); // Book is available
        CreateReservationRequest request = new CreateReservationRequest(1L, 1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // When & Then
        RecordConflictException exception = assertThrows(RecordConflictException.class,
                () -> reservationService.createReservation(request));
        assertThat(exception.getMessage()).isEqualTo("Book is available for loan, reservation is not possible.");
    }

    @Test
    void createReservation_AlreadyLoaned_ShouldThrowConflict() {
        // Given
        CreateReservationRequest request = new CreateReservationRequest(1L, 1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(loanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.of(new BookLoan()));

        // When & Then
        RecordConflictException exception = assertThrows(RecordConflictException.class,
                () -> reservationService.createReservation(request));
        assertThat(exception.getMessage()).isEqualTo("You have already loaned this book.");
    }

    @Test
    void createReservation_AlreadyReserved_ShouldThrowConflict() {
        // Given
        CreateReservationRequest request = new CreateReservationRequest(1L, 1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(loanRepository.findByBookIdAndMemberId(1L, 1L)).thenReturn(Optional.empty());
        when(reservationRepository.findByBookIdAndMemberIdAndStatusIn(anyLong(), anyLong(), anyList()))
                .thenReturn(Optional.of(reservation));

        // When & Then
        RecordConflictException exception = assertThrows(RecordConflictException.class,
                () -> reservationService.createReservation(request));
        assertThat(exception.getMessage()).isEqualTo("You already have an active reservation for this book.");
    }

    @Test
    void createReservation_BookNotFound_ShouldThrowNotFound() {
        // Given
        CreateReservationRequest request = new CreateReservationRequest(99L, 1L);
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class,
                () -> reservationService.createReservation(request));
        assertThat(exception.getMessage()).isEqualTo("Book not found with id: 99");
    }

    // ZMIENIONY TEST
    @Test
    void cancelReservation_Success() {
        // Given
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        BookReservationServiceImpl spyReservationService = spy(reservationService);

        // When
        spyReservationService.cancelReservation(1L, 1L);

        // Then
        verify(reservationRepository, times(1)).delete(reservation);
        // Weryfikujemy na szpiegu, a nie na oryginalnym obiekcie
        verify(spyReservationService, never()).processNextReservationForBook(any());
    }

    // ZMIENIONY TEST
    @Test
    void cancelReservation_WaitingForPickup_ShouldProcessNext() {
        // Given
        reservation.setStatus(ReservationStatus.WAITING_FOR_PICKUP);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        BookReservationServiceImpl spyReservationService = spy(reservationService);
        // Zaślepienie metody na szpiegu, aby uniknąć jej faktycznego wykonania
        doNothing().when(spyReservationService).processNextReservationForBook(any(Book.class));

        // When
        spyReservationService.cancelReservation(1L, 1L);

        // Then
        verify(reservationRepository, times(1)).delete(reservation);
        // Weryfikujemy wywołanie na szpiegu
        verify(spyReservationService, times(1)).processNextReservationForBook(book);
    }

    @Test
    void cancelReservation_WrongMember_ShouldThrowSecurityException() {
        // Given
        Long wrongMemberId = 2L;
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // When & Then
        SecurityException exception = assertThrows(SecurityException.class,
                () -> reservationService.cancelReservation(1L, wrongMemberId));
        assertThat(exception.getMessage()).isEqualTo("You are not allowed to cancel this reservation.");
    }

    @Test
    void processNextReservationForBook_NextReservationExists() {
        // Given
        BookReservation nextReservation = BookReservation.builder().id(2L).status(ReservationStatus.ACTIVE).build();
        when(reservationRepository.findFirstByBookIdAndStatusOrderByReservationDateAsc(book.getId(), ReservationStatus.ACTIVE))
                .thenReturn(Optional.of(nextReservation));

        // When
        reservationService.processNextReservationForBook(book);

        // Then
        verify(reservationRepository, times(1)).save(nextReservation);
        assertThat(nextReservation.getStatus()).isEqualTo(ReservationStatus.WAITING_FOR_PICKUP);
        assertThat(nextReservation.getPickupDeadline()).isNotNull();
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void processNextReservationForBook_NoNextReservation() {
        // Given
        when(reservationRepository.findFirstByBookIdAndStatusOrderByReservationDateAsc(book.getId(), ReservationStatus.ACTIVE))
                .thenReturn(Optional.empty());
        int initialQuantity = book.getQuantity();

        // When
        reservationService.processNextReservationForBook(book);

        // Then
        verify(bookRepository, times(1)).save(book);
        assertThat(book.getQuantity()).isEqualTo(initialQuantity + 1);
        verify(reservationRepository, never()).save(any(BookReservation.class));
    }
}