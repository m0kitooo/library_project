package com.app.libraryproject.dto.bookUsage;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.OnSiteLoan;
import com.app.libraryproject.model.LoanStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OnSiteLoanResponse(
        Long id,
        MemberResponse member,
        List<BookResponse> books,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LoanStatus status
) {
    public static OnSiteLoanResponse from(OnSiteLoan onSiteLoan) {
        return OnSiteLoanResponse.builder()
                .id(onSiteLoan.getId())
                .member(onSiteLoan.getMember().toMemberResponse())
                .books(onSiteLoan.getBooks().stream()
                        .map(BookResponse::from)
                        .toList())
                .startTime(onSiteLoan.getStartTime())
                .endTime(onSiteLoan.getEndTime())
                .status(onSiteLoan.getStatus())
                .build();
    }
}
