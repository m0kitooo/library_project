package com.app.libraryproject.service;

import com.app.libraryproject.dto.disposedbook.DisposedBookResponse;
import com.app.libraryproject.entity.Book;

import java.util.List;

public interface DisposedBookService {
    // Dodaje książkę do listy utylizacyjnej
    void addDisposedBook(Book book);

    // Pobiera listę wszystkich książek do utylizacji
    List<DisposedBookResponse> getAllDisposedBooks();

    // Oznacza jedną sztukę jako zutylizowaną (dekrementuje licznik)
    void markAsUtilized(Long disposedBookId);

    // Oznacza wszystkie sztuki jako zutylizowane (usuwa rekord)
    void utilizeAll(Long disposedBookId);
}