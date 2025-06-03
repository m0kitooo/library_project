package com.app.libraryproject.dto;

import java.time.LocalDate;

public record BookLoanDto(
        AuthorDto author,
        CreateBookRequest book,
        LocalDate loanDate
) { }
