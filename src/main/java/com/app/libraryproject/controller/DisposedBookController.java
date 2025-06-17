package com.app.libraryproject.controller;

import com.app.libraryproject.dto.disposedbook.DisposedBookResponse;
import com.app.libraryproject.service.DisposedBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disposed-books")
@CrossOrigin
@RequiredArgsConstructor
public class DisposedBookController {

    private final DisposedBookService disposedBookService;

    @GetMapping
    public ResponseEntity<List<DisposedBookResponse>> getAllDisposedBooks() {
        return ResponseEntity.ok(disposedBookService.getAllDisposedBooks());
    }

    @PostMapping("/{id}/utilize")
    public ResponseEntity<Void> markAsUtilized(@PathVariable Long id) {
        disposedBookService.markAsUtilized(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> utilizeAll(@PathVariable Long id) {
        disposedBookService.utilizeAll(id);
        return ResponseEntity.noContent().build();
    }
}