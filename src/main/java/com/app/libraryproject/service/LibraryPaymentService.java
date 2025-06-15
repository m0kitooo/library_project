package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;

import java.util.List;

public interface LibraryPaymentService {
    LibraryPaymentResponse register(CreateLibraryPaymentRequest request);
    List<LibraryPaymentResponse> findAll();
}
