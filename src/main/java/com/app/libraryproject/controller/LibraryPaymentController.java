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

    @GetMapping
    public List<LibraryPaymentResponse> getAllPayments() {
        return libraryPaymentService.findAll();
    }

    @PostMapping
    public LibraryPaymentResponse save(@RequestBody CreateLibraryPaymentRequest request) {
        return libraryPaymentService.register(request);
    }
}
