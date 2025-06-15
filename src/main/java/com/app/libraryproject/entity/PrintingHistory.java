package com.app.libraryproject.entity;

import com.app.libraryproject.model.PrintingColor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "printing_history")
public class PrintingHistory {
    @Id
    private Long id;
    private LocalDateTime printingTime;
    private int numberOfPages;
    private int numberOfCopies;
    private PrintingColor printingColor;
    @OneToOne
    private Payment payment;
}
