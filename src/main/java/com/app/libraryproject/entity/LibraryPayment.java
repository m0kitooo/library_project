package com.app.libraryproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Check;

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

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @Column(nullable = false)
    @Pattern(regexp = "^\\d{10,}$", message = "NIP has to be at least 10 digits length")
    private String nip;

    @Column(name = "brutto_cost", nullable = false)
    private BigDecimal bruttoCost;

    @Column(nullable = false)
    private Integer vat;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    @Check(constraints = "quantity > 0")
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
