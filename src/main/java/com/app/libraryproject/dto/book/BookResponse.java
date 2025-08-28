package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import lombok.Builder;

@Builder
public record BookResponse(
        Long id,
        String isbn,
        String title,
        String author,
        String publisher,
        String edition,
        Integer publicationYear
) {
    public static BookResponse from(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .edition(book.getEdition())
                .publicationYear(book.getPublicationYear())
                .build();
    }
}
