package com.app.libraryproject.controller;

import com.app.libraryproject.dto.printingHistory.PrintingHistoryRequest;
import com.app.libraryproject.dto.printingHistory.PrintingHistoryResponse;
import com.app.libraryproject.service.PrintingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/printings")
@RequiredArgsConstructor
public class PrintingController {

    private final PrintingService printingService;

    @PostMapping
    public ResponseEntity<PrintingHistoryResponse> createPrinting(@RequestBody PrintingHistoryRequest request) {
        PrintingHistoryResponse response = printingService.createPrinting(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PrintingHistoryResponse>> getAllPrintings() {
        return ResponseEntity.ok(printingService.getAllPrintings());
    }
}
