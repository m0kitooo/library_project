package com.app.libraryproject.dto.user;

import com.app.libraryproject.entity.User;
import lombok.Builder;

@Builder
public record PersonResponse(
        Long id,
        String name,
        String surname
) {
    public static PersonResponse from(User user) {
        return null;
//        return PersonResponse
//                .builder()
//                .id(user.getId())
//                .name(user.getName())
//                .surname(user.getSurname())
//                .build();
    }
}
