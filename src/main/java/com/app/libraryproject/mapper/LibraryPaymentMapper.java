package com.app.libraryproject.mapper;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LibraryPaymentMapper {
    private final UserRepository userRepository;

    public LibraryPayment toLibraryPayment(CreateLibraryPaymentRequest createLibraryPaymentRequest) {
        return LibraryPayment
                .builder()
                .transactionName(createLibraryPaymentRequest.transactionName())
                .cost(createLibraryPaymentRequest.cost())
                .description(createLibraryPaymentRequest.description())
                .user(userRepository
                        .findById(createLibraryPaymentRequest.userId())
                        .orElseThrow(() -> new IllegalArgumentException("Unable to map, user with such id doesn't exist"))
                ).build();
    }
}
