package com.app.libraryproject.dto.bookreturn;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;

import java.time.LocalDateTime;

public record BookReturnResponse(
        Long id,
        MemberResponse memberResponse,
        BookResponse bookResponse,
        LocalDateTime loanDate
) { }
