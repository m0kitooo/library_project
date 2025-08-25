package com.app.libraryproject.dto.user;

import com.app.libraryproject.entity.User;
import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String username
) {
    public static UserResponse from(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
