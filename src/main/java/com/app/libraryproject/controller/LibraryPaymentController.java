package com.app.libraryproject.controller;

import com.app.libraryproject.dto.librarypayment.CreateLibraryPaymentRequest;
import com.app.libraryproject.dto.librarypayment.LibraryPaymentResponse;
import com.app.libraryproject.service.LibraryPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library-payments")
@RequiredArgsConstructor
public class LibraryPaymentController {
    private final LibraryPaymentService libraryPaymentService;

    @GetMapping("/{id}")
    public LibraryPaymentResponse getLibraryPayment(@PathVariable Long id) {
        return libraryPaymentService.findById(id);
    }

    @GetMapping
    public List<LibraryPaymentResponse> getAllPayments() {
        return libraryPaymentService.findAll();
    }

    @GetMapping(params = "transaction_name")
    public List<LibraryPaymentResponse> getPaymentsByTransactionName(
            @RequestParam("transaction_name") String transactionName
    ) {
        return libraryPaymentService.findByTransactionName(transactionName);
    }

    @GetMapping(params = "phrase")
    public List<LibraryPaymentResponse> getPaymentsByPhrase(@RequestParam String phrase) {
        return libraryPaymentService.findByPhrase(phrase);
    }

    @PostMapping
    public LibraryPaymentResponse save(@RequestBody CreateLibraryPaymentRequest request) {
        return libraryPaymentService.register(request);
    }
}
