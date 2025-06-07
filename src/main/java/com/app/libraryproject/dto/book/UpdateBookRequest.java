package com.app.libraryproject.dto.book;

public record UpdateBookRequest(
        Long id,
        String title,
        String description,
        Integer quantity
) { }
