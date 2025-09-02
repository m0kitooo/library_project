package com.app.libraryproject.dto.bookreturn;

public record CreateBookReturnRequest(
    Long memberId,
    Long bookId
) {}
