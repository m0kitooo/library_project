package com.app.libraryproject.dto.user;

import com.app.libraryproject.entity.User;
import lombok.Builder;

@Builder
public record PersonResponse(
        Long id,
        String name,
        String surname
) {
}
