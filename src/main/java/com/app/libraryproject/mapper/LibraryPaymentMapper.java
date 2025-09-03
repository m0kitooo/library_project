package com.app.libraryproject.mapper;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.entity.User;

public interface LibraryPaymentMapper {

    static LibraryPayment toLibraryPayment(CreateLibraryPaymentRequest createLibraryPaymentRequest, User user) {
        return LibraryPayment
                .builder()
                .transactionName(createLibraryPaymentRequest.transactionName())
                .transactionDate(createLibraryPaymentRequest.transactionDate())
                .vendor(createLibraryPaymentRequest.vendor())
                .invoiceNumber(createLibraryPaymentRequest.invoiceNumber())
                .nip(createLibraryPaymentRequest.nip())
                .bruttoCost(createLibraryPaymentRequest.bruttoCost())
                .vat(createLibraryPaymentRequest.vat())
                .currency(createLibraryPaymentRequest.currency())
                .quantity(createLibraryPaymentRequest.quantity())
                .user(user)
                .build();
    }
}
