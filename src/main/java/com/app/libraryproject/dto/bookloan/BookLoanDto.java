package com.app.libraryproject.dto.bookloan;

import com.app.libraryproject.dto.book.CreateBookRequest;

import java.time.LocalDate;

public record BookLoanDto(
        CreateBookRequest book,
        LocalDate loanDate
) { }
