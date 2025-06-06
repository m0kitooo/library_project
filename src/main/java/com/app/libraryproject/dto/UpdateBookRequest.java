package com.app.libraryproject.dto;

public record UpdateBookRequest(
        Long id,
        String title,
        String description,
        Integer quantity
) { }
