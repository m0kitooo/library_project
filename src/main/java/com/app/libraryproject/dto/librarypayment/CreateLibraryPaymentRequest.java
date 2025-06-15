package com.app.libraryproject.dto.librarypayment;

import com.app.libraryproject.entity.LibraryPayment;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateLibraryPaymentRequest(
        String transactionName,
        BigDecimal cost,
        String description
) {
    public static CreateLibraryPaymentRequest from(LibraryPayment libraryPayment) {
        return CreateLibraryPaymentRequest
                .builder()
                .transactionName(libraryPayment.getTransactionName())
                .cost(libraryPayment.getCost())
                .description(libraryPayment.getDescription())
                .build();
    }

    public LibraryPayment toLibraryPayment() {
        return LibraryPayment
                .builder()
                .transactionName(transactionName)
                .cost(cost)
                .description(description)
                .build();
    }
}
