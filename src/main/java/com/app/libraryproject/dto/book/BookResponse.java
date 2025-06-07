package com.app.libraryproject.dto.book;

public record BookResponse(
        Long id,
        String title,
        String author,
        String description
) { }
