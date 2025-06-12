package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;
import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.repository.LibraryPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryPaymentServiceImpl implements LibraryPaymentService {
    private final LibraryPaymentRepository libraryPaymentRepository;

    @Override
    public List<LibraryPaymentResponse> findAll() {
        return libraryPaymentRepository
                .findAll()
                .stream()
                .map(LibraryPayment::toDto)
                .toList();
    }
}
