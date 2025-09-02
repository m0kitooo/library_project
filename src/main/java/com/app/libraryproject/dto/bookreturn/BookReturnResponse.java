package com.app.libraryproject.dto.bookreturn;

import java.math.BigDecimal;

public record BookReturnResponse(
    BigDecimal lateFee
) {}
