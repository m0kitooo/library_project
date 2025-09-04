package com.app.libraryproject.controller;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import com.app.libraryproject.dto.bookreturn.CreateBookReturnRequest;
import com.app.libraryproject.service.BookReturnServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class BookReturnController {
    private final BookReturnServiceImpl bookReturnServiceImpl;

    @PostMapping("/return")
    public ResponseEntity<BookReturnResponse> returnBook(@RequestBody CreateBookReturnRequest request) {
        return ResponseEntity.ok(bookReturnServiceImpl.returnBook(request));
    }
}
