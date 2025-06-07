package com.app.libraryproject.dto.bookloan;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;

import java.time.LocalDate;

public record BookLoanResponse(
        Long id,
        MemberResponse memberResponse,
        BookResponse bookResponse,
        LocalDate loanDate
) { }
