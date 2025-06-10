package com.app.libraryproject.controller;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.book.CreateBookRequest;
import com.app.libraryproject.dto.book.UpdateBookRequest;
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

    @GetMapping(params = "id")
    public BookResponse getBook(@RequestParam Long id) {
        return bookService.find(id);
    }

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.findAll();
    }

    @GetMapping(params = "title")
    public List<BookResponse> getBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTitle(title);
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody CreateBookRequest createBookRequest) {
        return new ResponseEntity<>(bookService.registerBook(createBookRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BookResponse> updateBook(@RequestBody UpdateBookRequest updateBookRequest) {
        return new ResponseEntity<>(bookService.updateBook(updateBookRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BookResponse> deleteBook(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }
}
