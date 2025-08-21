package com.app.libraryproject.dto.bookloan;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.BookLoan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookLoanResponse(
        Long id,
        @JsonProperty("member") MemberResponse memberResponse,
        @JsonProperty("book") BookResponse bookResponse,
        LocalDate loanDate,
        Boolean archived
) {
    public static BookLoanResponse from(BookLoan bookLoan) {
        return BookLoanResponse
                .builder()
                .id(bookLoan.getId())
                .memberResponse(MemberResponse.from(bookLoan.getMember()))
                .bookResponse(BookResponse.from(bookLoan.getBook()))
                .loanDate(bookLoan.getLoanDate())
                .archived(bookLoan.isArchived())
                .build();
    }
}
