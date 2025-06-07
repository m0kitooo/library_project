package com.app.libraryproject.dto.bookdisposal;

import com.app.libraryproject.dto.book.CreateBookRequest;

import java.time.LocalDate;

public record CreateBookDisposalRequest(
        CreateBookRequest book,
        LocalDate disposalDate
) { }
