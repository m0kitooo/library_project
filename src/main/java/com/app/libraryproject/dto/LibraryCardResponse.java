package com.app.libraryproject.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCardResponse {
    private Long id;
    private LocalDate expiryDate;
}
