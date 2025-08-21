package com.app.libraryproject.controller;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.dto.bookloan.CreateBookLoanRequest;
import com.app.libraryproject.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping("/book-loans")
@RequiredArgsConstructor
public class BookLoanController {
    private final BookLoanService bookLoanService;

    // @GetMapping
    // public List<BookLoanResponseWithArchived> getAllBookLoans() {
    //     return bookLoanService.getAllBookLoan();
    // }

    @GetMapping("/book-loans")
    public List<BookLoanResponse> getBookLoans(@RequestParam(required = false) Boolean archived) {
        return bookLoanService.getBookLoans(archived);
    }

    @GetMapping("/members/{memberId}/book-loans")
    public List<BookLoanResponse> getBookLoansByMember(
            @PathVariable Long memberId,
            @RequestParam(required = false) Boolean archived
    ) {
        return bookLoanService.getBookLoansByMember(memberId, archived);
    }

    @GetMapping("/books/{bookId}/book-loans")
    public List<BookLoanResponse> getBookLoanByBookId(@PathVariable Long bookId) {
        return bookLoanService.findByBookId(bookId);
    }

    @PostMapping("/book-loans")
    public ResponseEntity<BookLoanResponse> makeBookLoan(@RequestBody CreateBookLoanRequest request) {
        return new ResponseEntity<>(bookLoanService.loanBook(request), HttpStatus.CREATED);
    }
}
