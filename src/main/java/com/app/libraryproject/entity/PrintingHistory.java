package com.app.libraryproject.entity;

import com.app.libraryproject.model.PrintingColor;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime printingTime;

    private int numberOfPages;

    private int numberOfCopies;

    @Enumerated(EnumType.STRING)
    private PrintingColor printingColor;

    @OneToOne(cascade = CascadeType.ALL)
    private XeroPayment payment;
}
