package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "vendor", nullable = false, length = 100)
    private String vendor;

    @Column(name = "invoice_number", length = 50)
    private String invoiceNumber;

    @Column(nullable = false, length = 10)
    private String nip;

    @Column(name = "brotto_cost", nullable = false)
    private BigDecimal bruttoCost;

    @Column(nullable = false)
    private Integer vat;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
