package com.app.libraryproject.dto.bookUsage;

import java.util.List;

public record OnSiteLoanRequest(
        Long libraryCardId,
        List<Long> booksId
) {
}
