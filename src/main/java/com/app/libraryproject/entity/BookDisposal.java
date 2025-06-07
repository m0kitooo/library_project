package com.app.libraryproject.entity;

import com.app.libraryproject.dto.bookdisposal.BookDisposalResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "book_disposals")
public class BookDisposal {
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
    private LocalDateTime disposalDate;

    public BookDisposalResponse toBookDisposalResponse() {
        return new BookDisposalResponse(id, book.toBookResponse(), member.toMemberResponse(), disposalDate);
    }
}
