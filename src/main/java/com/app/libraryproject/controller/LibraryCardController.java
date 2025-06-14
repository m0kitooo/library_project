package com.app.libraryproject.controller;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.service.LibraryCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-cards")
@CrossOrigin
@RequiredArgsConstructor
public class LibraryCardController {
    private final LibraryCardServiceImpl libraryCardService;

    @GetMapping("/{id}")
    public LibraryCardResponse getLibraryCardDetails(@PathVariable Long id) {
        return libraryCardService.getLibraryCardDetails(id);
    }

    @PostMapping
    public ResponseEntity<Long> createLibraryCard(@RequestBody CreateLibraryCardRequest request) {
        return new ResponseEntity<>(libraryCardService.registerLibraryCard(request), HttpStatus.CREATED);
    }
}