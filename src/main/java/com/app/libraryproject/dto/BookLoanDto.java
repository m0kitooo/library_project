package com.app.libraryproject.dto;

import java.time.LocalDate;

public record BookLoanDto(
        CreateBookRequest book,
        LocalDate loanDate
) { }
