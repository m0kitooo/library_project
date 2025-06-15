package com.app.libraryproject.controller;

import com.app.libraryproject.dto.user.*;
import com.app.libraryproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping
    public List<PersonResponse> getUserList(@RequestBody GetPersonListRequest request) {
        return userService.getUserList(request);
    }

    @PostMapping
    public void register(@RequestBody CreateUserRequest request) {
        userService.register(request);
    }
}