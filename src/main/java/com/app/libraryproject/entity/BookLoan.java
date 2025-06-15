package com.app.libraryproject.entity;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "book_loans")
public class BookLoan {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "loan_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate loanDate;

    @Column(name = "return_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate returnDate;

    private boolean onSite;

    public BookLoanResponse toBookLoanResponse() {
        return new BookLoanResponse(id, member.toMemberResponse(), book.toBookResponse(), loanDate);
    }
}
