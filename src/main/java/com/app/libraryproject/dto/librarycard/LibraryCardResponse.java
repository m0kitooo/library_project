package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.MemberResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LibraryCardResponse(
        Long id,
        @JsonProperty("member") MemberResponse memberResponse,
        LocalDate expiryDate
) { }
