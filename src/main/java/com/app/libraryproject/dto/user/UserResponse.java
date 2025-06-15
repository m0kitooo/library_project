package com.app.libraryproject.dto.user;

import com.app.libraryproject.entity.User;

public record UserResponse() {
    public static UserResponse from(User user) {
        return new UserResponse();
    }
}
