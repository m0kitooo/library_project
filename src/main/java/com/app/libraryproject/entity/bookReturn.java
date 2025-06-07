package com.app.libraryproject.entity;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "book_returns")
public class bookReturn {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime returnDate;

    public BookReturnResponse toBookReturnResponse() {
        return new BookReturnResponse(id, member.toMemberResponse(), book.toBookResponse(), returnDate);
    }
}
