package com.app.libraryproject.controller;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.service.LibraryCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/libraryCard")
@CrossOrigin
public class LibraryCardController {
    private final LibraryCardServiceImpl libraryCardService;

    @PostMapping("/create")
    public ResponseEntity<LibraryCardResponse> createLibraryCard(@RequestBody CreateLibraryCardRequest request) {
        return new ResponseEntity<>(libraryCardService.registerLibraryCard(request), HttpStatus.CREATED);
    }

    @PostMapping("/details")
    public ResponseEntity<GetLibraryCardDetailsResponse> getLibraryCardDetails(@RequestParam Long libraryCardId) {
        return ResponseEntity.ok(
                libraryCardService.getLibraryCardDetails(libraryCardId)
        );
    }
}