package com.app.libraryproject.service;

import com.app.libraryproject.entity.PrintingHistory;

import java.util.List;

public interface PrintingHistoryService {
    List<PrintingHistory> getAll();
    PrintingHistory newPrinting(int numberOfPages, int numberOfCopies, char printingCollor);
}
