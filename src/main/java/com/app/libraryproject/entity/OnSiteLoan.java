package com.app.libraryproject.entity;

import com.app.libraryproject.dto.bookUsage.OnSiteLoanResponse;
import com.app.libraryproject.model.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "on_site_loans")
public class OnSiteLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Member member;

    @ManyToMany
    private List<Book> books;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public OnSiteLoanResponse toOnSiteLoanResponse() {
        return OnSiteLoanResponse.builder()
                .id(id)
                .member(member.toMemberResponse())
                .books(books.stream()
                        .map(Book::toBookResponse)
                        .toList())
                .startTime(startTime)
                .endTime(endTime)
                .status(status)
                .build();
    }
}

