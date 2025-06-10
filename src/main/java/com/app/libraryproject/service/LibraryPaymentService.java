package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;

import java.util.List;

public interface LibraryPaymentService {
    List<LibraryPaymentResponse> findAll();
}
