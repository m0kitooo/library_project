package com.app.libraryproject.controller;

import com.app.libraryproject.dto.BookResponse;
import com.app.libraryproject.dto.CreateBookRequest;
import com.app.libraryproject.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody CreateBookRequest createBookRequest) {
        return new ResponseEntity<>(bookService.registerBook(createBookRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BookResponse> deleteBook(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }
}
