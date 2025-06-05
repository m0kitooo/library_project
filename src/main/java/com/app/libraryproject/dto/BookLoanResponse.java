package com.app.libraryproject.dto;

import java.time.LocalDate;

public record BookLoanResponse(
        Long id,
        MemberResponse memberResponse,
        BookResponse bookResponse,
        LocalDate loanDate
) { }
