package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreservation.CreateReservationRequest;
import com.app.libraryproject.dto.bookreservation.ReservationResponse;
import com.app.libraryproject.entity.Book;

import java.util.List;

public interface BookReservationService {
    ReservationResponse createReservation(CreateReservationRequest request);
    void cancelReservation(Long reservationId, Long memberId);
    List<ReservationResponse> findReservationsByMember(Long memberId);
    void processNextReservationForBook(Book book);
    List<ReservationResponse> findAllReservations();
}