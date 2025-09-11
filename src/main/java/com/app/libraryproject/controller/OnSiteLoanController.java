package com.app.libraryproject.controller;

import com.app.libraryproject.dto.bookUsage.FinishOnSiteLoanRequest;
import com.app.libraryproject.dto.bookUsage.OnSiteLoanRequest;
import com.app.libraryproject.dto.bookUsage.OnSiteLoanResponse;
import com.app.libraryproject.service.OnSiteLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onsite")
@RequiredArgsConstructor
public class OnSiteLoanController {

    private final OnSiteLoanService onSiteLoanService;

    @GetMapping
    public ResponseEntity<List<OnSiteLoanResponse>> getAllOnSiteLoan() {
        return ResponseEntity.ok(onSiteLoanService.getLoans());
    }

    @PostMapping("/add")
    public ResponseEntity<List<OnSiteLoanResponse>> addOnSiteLoan(@RequestBody OnSiteLoanRequest request) {
        return ResponseEntity.ok(onSiteLoanService.addOnSiteLoan(request));
    }

    @PostMapping("/finish")
    public ResponseEntity<OnSiteLoanResponse> finishOnSiteLoan(@RequestBody FinishOnSiteLoanRequest request) {
        return ResponseEntity.ok(onSiteLoanService.finishOnSiteLoan(request));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<OnSiteLoanResponse>> getMemberLoans(@PathVariable Long memberId) {
        return ResponseEntity.ok(onSiteLoanService.getLoansForMember(memberId));
    }
}

