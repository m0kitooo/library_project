package com.app.libraryproject.dto;

import java.time.LocalDate;

public record BookLoanDto(
        AuthorDto author,
        BookDto book,
        LocalDate loanDate
) { }
