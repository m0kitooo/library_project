package com.app.libraryproject.controller;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.service.LibraryCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/libraryCard")
@CrossOrigin(origins = "http://localhost:5173")
public class LibraryCardController {
    private final LibraryCardServiceImpl libraryCardService;

    @PostMapping("/create")
    public ResponseEntity<Void> createLibraryCard(@RequestBody CreateLibraryCardRequest request) {
        libraryCardService.registerLibraryCard(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/details")
    public ResponseEntity<GetLibraryCardDetailsResponse> getLibraryCardDetails(@RequestParam Long libraryCardId) {
        return ResponseEntity.ok(
                libraryCardService.getLibraryCardDetails(libraryCardId)
        );
    }
}