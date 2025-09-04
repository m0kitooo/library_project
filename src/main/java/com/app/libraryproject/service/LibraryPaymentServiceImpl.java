package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;
import com.app.libraryproject.exception.RecordNotFoundException;
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
    public LibraryPaymentResponse findById(Long id) {
        return LibraryPaymentResponse.from(
                libraryPaymentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Library Payment not found"))
        );
    }

    @Override
    public List<LibraryPaymentResponse> findAll() {
        return libraryPaymentRepository
                .findAll()
                .stream()
                .map(LibraryPaymentResponse::from)
                .toList();
    }

    @Override
    public List<LibraryPaymentResponse> findByTransactionName(String transactionName) {
        return libraryPaymentRepository
                .findByTransactionNameContainingIgnoreCase(transactionName)
                .stream()
                .map(LibraryPaymentResponse::from)
                .toList();
    }

    @Override
    public List<LibraryPaymentResponse> findByPhrase(String phrase) {
        return libraryPaymentRepository
                .findByPhrase(phrase)
                .stream()
                .map(LibraryPaymentResponse::from)
                .toList();
    }
}
