package com.app.libraryproject.validate;

import com.app.libraryproject.dto.CreateBookRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBookRequestValidator {
    public static boolean isValid(CreateBookRequest createBookRequest) {
        return createBookRequest.quantity() >= 0 && isTitleValid(createBookRequest.title());
    }

    private static boolean isTitleValid(String title) {
        return title != null && !title.isBlank();
    }
}
