package com.app.libraryproject.controller;

import com.app.libraryproject.dto.*;
import com.app.libraryproject.service.LibraryCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/libraryCard")
//@CrossOrigin(origins = "")
public class LibraryCardController {

    private final LibraryCardServiceImpl libraryCardService;

    @PostMapping("/create")
    public ResponseEntity<LibraryCardResponse> createLibraryCard(@RequestBody CreateLibraryCardRequest request) {
        return new ResponseEntity<>(libraryCardService.register(request), HttpStatus.CREATED);
    }
}