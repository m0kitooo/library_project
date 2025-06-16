package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "library_payments")
public class LibraryPayment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "transaction_name", nullable = false)
    private String transactionName;

    @Column(nullable = false)
    private BigDecimal cost;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
