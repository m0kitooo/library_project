package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;
import com.app.libraryproject.entity.LibraryPayment;
import com.app.libraryproject.mapper.LibraryPaymentMapper;
import com.app.libraryproject.repository.LibraryPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryPaymentServiceImpl implements LibraryPaymentService {
    private final LibraryPaymentRepository libraryPaymentRepository;
    private final LibraryPaymentMapper libraryPaymentMapper;

    @Override
    public LibraryPaymentResponse register(CreateLibraryPaymentRequest request) {
        return LibraryPaymentResponse.from(libraryPaymentRepository
                .save(libraryPaymentMapper.toLibraryPayment(request)));
    }

    @Override
    public List<LibraryPaymentResponse> findAll() {
        return libraryPaymentRepository
                .findAll()
                .stream()
                .map(LibraryPayment::toDto)
                .toList();
    }
}
