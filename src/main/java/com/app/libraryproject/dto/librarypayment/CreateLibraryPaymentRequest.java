package com.app.libraryproject.dto.librarypayment;

import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import lombok.Builder;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Builder
public record CreateLibraryPaymentRequest(
        String transactionName,
        BigDecimal cost
) {
    public CreateLibraryPaymentRequest {
        if (isBlank(transactionName))
            throw new InvalidRequestArgumentException("transactionName cannot be null or blank");
        if (cost == null || cost.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidRequestArgumentException("cost cannot be null or negative");
    }

    public static CreateLibraryPaymentRequest from(LibraryPayment libraryPayment) {
        return CreateLibraryPaymentRequest
                .builder()
                .transactionName(libraryPayment.getTransactionName())
                .cost(libraryPayment.getCost())
                .build();
    }
}
