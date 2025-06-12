package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.MemberResponse;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LibraryCardResponse(
        Long id,
        MemberResponse member,
        LocalDate expiryDate
) { }
