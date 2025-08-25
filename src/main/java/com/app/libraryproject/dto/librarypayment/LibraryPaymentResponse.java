package com.app.libraryproject.dto.librarypayment;

import com.app.libraryproject.dto.user.UserResponse;
import com.app.libraryproject.entity.LibraryPayment;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LibraryPaymentResponse(
        Long id,
        String transactionName,
        BigDecimal cost,
        String description,
        UserResponse userResponse
) {
    public static LibraryPaymentResponse from(LibraryPayment libraryPayment) {
        return LibraryPaymentResponse
                .builder()
                .id(libraryPayment.getId())
                .transactionName(libraryPayment.getTransactionName())
                .cost(libraryPayment.getCost())
                .description(libraryPayment.getDescription())
                .userResponse(UserResponse.from(libraryPayment.getUser()))
                .build();
    }
}
