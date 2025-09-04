package com.app.libraryproject.controller;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.service.LibraryCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-cards")
@RequiredArgsConstructor
public class LibraryCardController {
    private final LibraryCardServiceImpl libraryCardService;

    @GetMapping("/{id}")
    public GetLibraryCardDetailsResponse getLibraryCardDetails(@PathVariable Long id) {
        return libraryCardService.getLibraryCardDetails(id);
    }

    @GetMapping(path = "/members/{memberId}/active-card")
    public LibraryCardResponse getActiveCardByMemberId(@PathVariable Long memberId) {
        return libraryCardService.getActiveLibraryCardByMemberId(memberId);
    }

    @PostMapping
    public ResponseEntity<LibraryCardResponse> createLibraryCard(@RequestBody CreateLibraryCardRequest request) {
        return new ResponseEntity<>(libraryCardService.registerLibraryCard(request), HttpStatus.CREATED);
    }
}
