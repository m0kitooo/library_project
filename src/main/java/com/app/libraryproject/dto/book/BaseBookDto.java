package com.app.libraryproject.dto.book;

public interface BaseBookDto {
    String isbn();
    String title();
    String author();
    String publisher();
    String edition();
    Integer publicationYear();
}
