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
@RequestMapping("/book-loans")
@CrossOrigin
@RequiredArgsConstructor
public class BookLoanController {
    private final BookLoanService bookLoanService;

    @GetMapping
    public List<BookLoanResponse> getAllBookLoans() {
        return bookLoanService.getAllBookLoan();
    }

    @PostMapping
    public ResponseEntity<BookLoanResponse> makeBookLoan(@RequestBody CreateBookLoanRequest request) {
        return new ResponseEntity<>(bookLoanService.loanBook(request.bookId(), request.memberId()), HttpStatus.CREATED);
    }
}
