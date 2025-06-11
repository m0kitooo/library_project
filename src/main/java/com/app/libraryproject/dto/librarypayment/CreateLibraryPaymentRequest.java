package com.app.libraryproject.dto.librarypayment;

import java.math.BigDecimal;

public record CreateLibraryPaymentRequest(
        String transactionName,
        BigDecimal cost,
        String description
) { }
