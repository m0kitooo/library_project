package com.app.libraryproject.dto.printingHistory;


import com.app.libraryproject.entity.PrintingHistory;
import com.app.libraryproject.entity.XeroPayment;
import com.app.libraryproject.model.PrintingColor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PrintingHistoryRequest(
        @Min(1) int numberOfPages,
        @Min(1) int numberOfCopies,
        @NotNull PrintingColor printingColor
) {
    public PrintingHistory toEntity(XeroPayment payment) {
        return PrintingHistory.builder()
                .printingTime(LocalDateTime.now())
                .numberOfPages(this.numberOfPages)
                .numberOfCopies(this.numberOfCopies)
                .printingColor(this.printingColor)
                .payment(payment)
                .build();
    }
}
