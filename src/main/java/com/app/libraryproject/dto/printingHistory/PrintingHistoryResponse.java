package com.app.libraryproject.dto.printingHistory;

import com.app.libraryproject.dto.payment.XeroPaymentResponse;
import com.app.libraryproject.entity.PrintingHistory;
import com.app.libraryproject.model.PrintingColor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PrintingHistoryResponse(
        Long id,
        LocalDateTime printingTime,
        int numberOfPages,
        int numberOfCopies,
        PrintingColor printingColor,
        BigDecimal payment
) {
    public static PrintingHistoryResponse from(PrintingHistory printingHistory) {
        return PrintingHistoryResponse.builder()
                .id(printingHistory.getId())
                .printingTime(printingHistory.getPrintingTime())
                .numberOfPages(printingHistory.getNumberOfPages())
                .numberOfCopies(printingHistory.getNumberOfCopies())
                .printingColor(printingHistory.getPrintingColor())
                .payment(
                        printingHistory.getPayment() != null
                                ? XeroPaymentResponse.from(printingHistory.getPayment()).amount()
                                : null
                )
                .build();
    }
}
