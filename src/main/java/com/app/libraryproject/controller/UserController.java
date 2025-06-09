package com.app.libraryproject.controller;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.proposal.GetUserListRequest;
import com.app.libraryproject.dto.user.GetUserListResponse;
import com.app.libraryproject.service.LibraryCardServiceImpl;
import com.app.libraryproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/list")
    public ResponseEntity<GetUserListResponse> createLibraryCard(@RequestBody GetUserListRequest request) {
        return ResponseEntity.ok(
                userService.getUserList(request)
        );
    }
}