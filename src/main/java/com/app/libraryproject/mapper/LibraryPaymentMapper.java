package com.app.libraryproject.mapper;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.entity.User;

public interface LibraryPaymentMapper {

    static LibraryPayment toLibraryPayment(CreateLibraryPaymentRequest createLibraryPaymentRequest, User user) {
        return LibraryPayment
                .builder()
                .transactionName(createLibraryPaymentRequest.transactionName())
                .cost(createLibraryPaymentRequest.cost())
                .description(createLibraryPaymentRequest.description())
                .user(user)
                .build();
    }
}
