package com.app.libraryproject.controller;

import com.app.libraryproject.dto.bookreservation.CreateReservationRequest;
import com.app.libraryproject.dto.bookreservation.ReservationResponse;
import com.app.libraryproject.service.BookReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin
@RequiredArgsConstructor
public class BookReservationController {

    private final BookReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        List<ReservationResponse> responses = reservationService.findAllReservations();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
        ReservationResponse response = reservationService.createReservation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ReservationResponse>> getMemberReservations(@PathVariable Long memberId) {
        List<ReservationResponse> responses = reservationService.findReservationsByMember(memberId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{reservationId}/member/{memberId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId, @PathVariable Long memberId) {
        reservationService.cancelReservation(reservationId, memberId);
        return ResponseEntity.noContent().build();
    }
}