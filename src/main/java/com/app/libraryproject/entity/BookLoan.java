package com.app.libraryproject.entity;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// it is imposible in hibernate to do such constraint but the thing to remember is that member should have only one active unique book loaned
// what i mean by that is u can't loan 2 books wiht same id at the same time

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "loan_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate loanDate;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean archived;

    public BookLoanResponse toBookLoanResponse() {
        return new BookLoanResponse(id, member.toMemberResponse(), book.toBookResponse(), loanDate, archived);
    }
}
