package com.app.libraryproject.entity;

import com.app.libraryproject.model.PaymentType;
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
@Table(name = "member_payments")
public class MemberPayment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_data", nullable = false)
    private LocalDate paymentDate;
}
