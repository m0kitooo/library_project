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

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "transaction_name", nullable = false)
    private String transactionName;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false, length = 10)
    @Pattern(regexp = "\\d{10}", message = "NIP must be exactly 10 digits")
    private String nip;

    @Column(name = "invoice_number", length = 50)
    private String invoiceNumber;

    @Lob
    private String description;

    @Column(nullable = false)
    @Check(constraints = "quantity > 0")
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
