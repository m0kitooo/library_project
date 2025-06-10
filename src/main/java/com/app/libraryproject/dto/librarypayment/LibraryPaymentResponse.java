package com.app.libraryproject.dto.librarypayment;

import com.app.libraryproject.dto.user.UserResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LibraryPaymentResponse(
        Long id,
        String transactionName,
        BigDecimal cost,
        String description,
        UserResponse userResponse
) { }
