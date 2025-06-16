package com.app.libraryproject.controller;

import com.app.libraryproject.dto.user.*;
import com.app.libraryproject.entity.User;
import com.app.libraryproject.service.AuthenticationService;
import com.app.libraryproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/me")
    public User getCurrentUser() {
        return authenticationService.getCurrentUser();
    }

    @GetMapping
    public List<PersonResponse> getUserList(@RequestBody GetPersonListRequest request) {
        return userService.getUserList(request);
    }

    @PostMapping
    public void register(@RequestBody CreateUserRequest request) {
        userService.register(request);
    }
}