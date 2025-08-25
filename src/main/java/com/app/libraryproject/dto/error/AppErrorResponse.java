package com.app.libraryproject.dto.error;

import com.app.libraryproject.model.error.ErrorCode;

public record AppErrorResponse(
    ErrorCode code,
    String message
) {}
