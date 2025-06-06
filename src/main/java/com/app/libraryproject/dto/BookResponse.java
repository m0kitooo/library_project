package com.app.libraryproject.dto;

public record BookResponse(
        Long id,
        String title,
        String description
) { }
