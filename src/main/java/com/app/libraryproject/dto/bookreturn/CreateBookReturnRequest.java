package com.app.libraryproject.dto.bookreturn;

import com.app.libraryproject.dto.book.CreateBookRequest;

import java.time.LocalDate;

public record CreateBookReturnRequest(
        CreateBookRequest book,
        LocalDate returnDate
) { }
