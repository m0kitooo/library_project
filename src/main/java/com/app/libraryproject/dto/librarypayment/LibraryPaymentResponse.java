package com.app.libraryproject.dto.librarypayment;

import com.app.libraryproject.entity.LibraryPayment;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record LibraryPaymentResponse(
        Long id,
        String transactionName,
        LocalDate transactionDate,
        String vendor,
        String invoiceNumber,
        String nip,
        BigDecimal bruttoCost,
        Integer vat,
        String currency,
        Integer quantity
) {
    public static LibraryPaymentResponse from(LibraryPayment libraryPayment) {
        return LibraryPaymentResponse
                .builder()
                .id(libraryPayment.getId())
                .transactionName(libraryPayment.getTransactionName())
                .transactionDate(libraryPayment.getTransactionDate())
                .vendor(libraryPayment.getVendor())
                .invoiceNumber(libraryPayment.getInvoiceNumber())
                .nip(libraryPayment.getNip())
                .bruttoCost(libraryPayment.getBruttoCost())
                .vat(libraryPayment.getVat())
                .currency(libraryPayment.getCurrency())
                .quantity(libraryPayment.getQuantity())
                .build();
    }
}
