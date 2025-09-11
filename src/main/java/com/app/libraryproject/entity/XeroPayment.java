package com.app.libraryproject.entity;

import com.app.libraryproject.dto.payment.XeroPaymentResponse;
import com.app.libraryproject.model.PaymentType;
import jakarta.persistence.*;
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
public class XeroPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PaymentType paymentType;

    private LocalDateTime paymentDate;

    private BigDecimal amount;

    private String description;

    public XeroPaymentResponse toPaymentResponse(){
        return new XeroPaymentResponse(id, paymentType, paymentDate, amount, description);
    }
}
