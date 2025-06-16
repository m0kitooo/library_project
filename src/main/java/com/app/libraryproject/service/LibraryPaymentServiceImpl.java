package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;
import com.app.libraryproject.repository.LibraryPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.libraryproject.mapper.LibraryPaymentMapper.toLibraryPayment;

@Service
@RequiredArgsConstructor
public class LibraryPaymentServiceImpl implements LibraryPaymentService {
    private final LibraryPaymentRepository libraryPaymentRepository;
    private final AuthenticationService authenticationService;

    @Override
    public LibraryPaymentResponse register(CreateLibraryPaymentRequest request) {
        return LibraryPaymentResponse.from(libraryPaymentRepository
                .save(toLibraryPayment(request, authenticationService.getCurrentUser())));
    }

    @Override
    public List<LibraryPaymentResponse> findAll() {
        return libraryPaymentRepository
                .findAll()
                .stream()
                .map(LibraryPaymentResponse::from)
                .toList();
    }
}
