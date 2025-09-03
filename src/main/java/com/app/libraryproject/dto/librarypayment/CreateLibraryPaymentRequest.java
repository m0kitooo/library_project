package com.app.libraryproject.dto.librarypayment;

import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Builder
public record CreateLibraryPaymentRequest(
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
    private final static String CURRENCY_REGEX = "^[A-Z]{3}$";
    private final static String NIP_REGEX = "^(\\d{10}|\\d{13})$";

    public CreateLibraryPaymentRequest {
        if (isBlank(transactionName))
            throw new InvalidRequestArgumentException("transactionName cannot be null or blank");
        if (isBlank(vendor))
            throw new InvalidRequestArgumentException("vendor cannot be null or blank");
        if (isBlank(invoiceNumber))
            throw new InvalidRequestArgumentException("invoiceNumber cannot be null or blank");
        if (bruttoCost == null || bruttoCost.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidRequestArgumentException("bruttoCost cannot be null or less than or equal to zero");
        if (vat == null || vat < 0 || vat > 100)
            throw new InvalidRequestArgumentException("vat cannot be null, less than 0 or greater than 100");
        if (currency == null || !currency.matches(CURRENCY_REGEX))
            throw new InvalidRequestArgumentException("currency must be a valid ISO 4217 currency code");
        if (quantity == null || quantity <= 0)
            throw new InvalidRequestArgumentException("quantity cannot be null or less than or equal to zero");
    }

    public static CreateLibraryPaymentRequest from(LibraryPayment libraryPayment) {
        return CreateLibraryPaymentRequest
                .builder()
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
