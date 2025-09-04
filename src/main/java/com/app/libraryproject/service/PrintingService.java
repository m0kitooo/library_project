package com.app.libraryproject.service;

import com.app.libraryproject.dto.printingHistory.PrintingHistoryRequest;
import com.app.libraryproject.dto.printingHistory.PrintingHistoryResponse;

import java.util.List;

public interface PrintingService {
    PrintingHistoryResponse createPrinting(PrintingHistoryRequest request);
    List<PrintingHistoryResponse> getAllPrintings();
}
