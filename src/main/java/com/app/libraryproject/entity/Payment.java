package com.app.libraryproject.entity;

import com.app.libraryproject.model.PaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    private Long id;
    private PaymentType paymentType;
    private LocalDateTime PaymentDate;
    private BigDecimal amount;
    private String description;
}
