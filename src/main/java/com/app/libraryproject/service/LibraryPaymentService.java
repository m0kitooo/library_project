package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;

import java.util.List;

public interface LibraryPaymentService {
    LibraryPaymentResponse register(CreateLibraryPaymentRequest request);
    LibraryPaymentResponse findById(Long id);
    List<LibraryPaymentResponse> findByTransactionName(String transactionName);
    List<LibraryPaymentResponse> findAll();
    List<LibraryPaymentResponse> findByPhrase(String phrase);
    LibraryPaymentResponse delete(Long id);
}
