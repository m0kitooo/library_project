package com.app.libraryproject.controller;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.book.CreateBookRequest;
import com.app.libraryproject.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody CreateBookRequest createBookRequest) {
        return new ResponseEntity<>(bookService.registerBook(createBookRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BookResponse> deleteBook(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }
}
