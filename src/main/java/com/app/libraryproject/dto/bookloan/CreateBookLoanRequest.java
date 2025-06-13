package com.app.libraryproject.dto.bookloan;

public record CreateBookLoanRequest(
        Long bookId,
        Long memberId
) { }
