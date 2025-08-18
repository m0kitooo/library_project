package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.dto.bookloan.CreateBookLoanRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.exception.ResourceConflictException;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.error.AppError;
import com.app.libraryproject.repository.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import static com.app.libraryproject.model.error.ErrorCode.BOOK_NOT_FOUND;
import static com.app.libraryproject.model.error.ErrorCode.LIBRARY_CARD_NOT_FOUND;
import static com.app.libraryproject.model.error.ErrorCode.MEMBER_IS_CURRENTLY_LOANING_SAME_BOOK;
import static com.app.libraryproject.model.error.ErrorCode.MEMBER_NOT_FOUND;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibraryCardRepository libraryCardRepository;

    @Override
    public List<BookLoanResponse> getAllBookLoan() {
        return bookLoanRepository
                .findAll()
                .stream()
                .map(BookLoanResponse::from)
                .toList();
    }

    @Override
    public List<BookLoanResponse> findByBookId(Long bookId) {
        return bookLoanRepository.findByBookId(bookId)
                .stream()
                .map(BookLoanResponse::from)
                .toList();
    }

    @Transactional
    @Override
    public BookLoanResponse loanBook(CreateBookLoanRequest request) {
        Book book = bookRepository
                .findAvailableBookById(request.bookId())
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(BOOK_NOT_FOUND, "Book not avalible or doesn't exist")));

        // check if the member is already loaning the book
        if (book.getBookLoans()
                .stream()
                .filter(bl -> bl.isArchived() == false)
                .anyMatch(bl -> bl.getMember().getId().equals(request.memberId()))) {
            throw new ResourceConflictException(new AppError(MEMBER_IS_CURRENTLY_LOANING_SAME_BOOK, "Book is already loaned by the member"));
        }

        libraryCardRepository.findActiveCardByMemberId(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(LIBRARY_CARD_NOT_FOUND, "Member doesn't have an active library card")));

        return bookLoanRepository.save(
                BookLoan
                        .builder()
                        .member(memberRepository
                                .findById(request.memberId())
                                .orElseThrow(() -> new ResourceNotFoundException(new AppError(MEMBER_NOT_FOUND, "Such member doesn't exist")))
                        )
                        .book(book)
                        .loanDate(LocalDate.now())
                        .build()
        ).toBookLoanResponse();
    }
}
