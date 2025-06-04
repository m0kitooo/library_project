package com.app.libraryproject.controller;

import com.app.libraryproject.dto.*;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.mapper.LibraryCardMapper;
import com.app.libraryproject.service.LibraryCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/libraryCard")
//@CrossOrigin(origins = "")
public class LibraryCardController {

    private final LibraryCardService libraryCardService;
    private final LibraryCardMapper libraryCardMapper;

    @PostMapping("/create")
    public ResponseEntity<CreateLibraryCardResponse> createLibraryCard(@RequestBody CreateLibraryCardRequest request) {

        LibraryCard libraryCard = libraryCardService.registerCard(request);
        CreateLibraryCardResponse response = libraryCardMapper.toResponse(libraryCard);

        return ResponseEntity.ok(response);
    }
}