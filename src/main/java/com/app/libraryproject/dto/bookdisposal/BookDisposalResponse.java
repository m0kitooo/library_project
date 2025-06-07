package com.app.libraryproject.dto.bookdisposal;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;

import java.time.LocalDateTime;

public record BookDisposalResponse(
        Long id,
        BookResponse bookResponse,
        MemberResponse memberResponse,
        LocalDateTime disposalTime
) { }
