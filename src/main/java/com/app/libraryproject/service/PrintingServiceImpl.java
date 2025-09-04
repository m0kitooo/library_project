package com.app.libraryproject.service;

import com.app.libraryproject.dto.printingHistory.PrintingHistoryRequest;
import com.app.libraryproject.dto.printingHistory.PrintingHistoryResponse;
import com.app.libraryproject.entity.PrintingHistory;
import com.app.libraryproject.entity.XeroPayment;
import com.app.libraryproject.model.PaymentType;
import com.app.libraryproject.model.PrintingColor;
import com.app.libraryproject.repository.PrintingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintingServiceImpl implements PrintingService{

    private final PrintingHistoryRepository printingHistoryRepository;

    @Override
    public PrintingHistoryResponse createPrinting(PrintingHistoryRequest request) {
        BigDecimal cost = calculateCost(request.numberOfPages(), request.numberOfCopies(), request.printingColor());

        XeroPayment payment = XeroPayment.builder()
                .paymentType(PaymentType.DEPOSIT)
                .paymentDate(LocalDateTime.now())
                .amount(cost)
                .description("Opłata za wydruk")
                .build();

        PrintingHistory printingHistory = PrintingHistory.builder()
                .printingTime(LocalDateTime.now())
                .numberOfPages(request.numberOfPages())
                .numberOfCopies(request.numberOfCopies())
                .printingColor(request.printingColor())
                .payment(payment)
                .build();

        printingHistoryRepository.save(printingHistory);

        return PrintingHistoryResponse.from(printingHistory);
    }

    @Override
    public List<PrintingHistoryResponse> getAllPrintings() {
        return printingHistoryRepository.findAll().stream()
                .map(PrintingHistoryResponse::from)
                .toList();
    }

    private BigDecimal calculateCost(int pages, int copies, PrintingColor color) {
        return color.getPrice().multiply(BigDecimal.valueOf((long) pages * copies));
    }
}

