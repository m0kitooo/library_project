package com.app.libraryproject.dto.error;

public record AppErrorResponse(
    String code,
    String message,
    String details
) {}
