package com.app.libraryproject.service;

import com.app.libraryproject.entity.Payment;
import com.app.libraryproject.entity.PrintingHistory;
import com.app.libraryproject.model.PrintingColor;
import com.app.libraryproject.repository.PrintingHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PrintingHistoryServiceImpl implements PrintingHistoryService{
    private PrintingHistoryRepository printingHistoryRepository;
    private PaymentService paymentService;

    @Override
    public List<PrintingHistory> getAll() {
        return printingHistoryRepository.findAll();
    }

    @Override
    public PrintingHistory newPrinting(int numberOfPages, int numberOfCopies, char printingCollor) {
        PrintingColor printingColor = printingCollor == 'c' ? PrintingColor.COLOR : PrintingColor.WHITE_AND_BLACK;
        Payment payment = paymentService.newPayment('i', printingCollor * numberOfPages * numberOfCopies, "Usługa ksero");
        return printingHistoryRepository.save(PrintingHistory.builder()
                .printingTime(LocalDateTime.now())
                .numberOfPages(numberOfPages)
                .numberOfCopies(numberOfCopies)
                .printingColor(printingColor)
                .payment(payment)
                .build());
    }
}
