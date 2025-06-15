package com.app.libraryproject.dto.user;

import com.app.libraryproject.entity.User;

public record CreateUserRequest(
        String username,
        String password
) {
    public User toUser() {
        return User
                .builder()
                .username(username)
                .password(password)
                .build();
    }
}
