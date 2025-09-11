package com.app.libraryproject.dto.payment;

import com.app.libraryproject.entity.XeroPayment;
import com.app.libraryproject.model.PaymentType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record XeroPaymentResponse(
        Long id,
        PaymentType paymentType,
        LocalDateTime paymentDate,
        BigDecimal amount,
        String description
) {
    public static XeroPaymentResponse from(XeroPayment xeroPayment){
        return XeroPaymentResponse
                .builder()
                .id(xeroPayment.getId())
                .paymentType(xeroPayment.getPaymentType())
                .paymentDate(xeroPayment.getPaymentDate())
                .amount(xeroPayment.getAmount())
                .description(xeroPayment.getDescription())
                .build();
    }
}
