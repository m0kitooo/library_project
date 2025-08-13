package com.app.libraryproject.model.error;
import com.app.libraryproject.dto.error.AppErrorResponse;

import lombok.Builder;

@Builder
public record AppError(
    ErrorCode code,
    String message
) {
    public static AppErrorResponse from(AppError error) {
        return new AppErrorResponse(
            error.code(),
            error.message()
        );
    }
}
