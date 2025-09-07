package com.app.libraryproject.controller;

import com.app.libraryproject.dto.computerUsage.*;
import com.app.libraryproject.entity.ComputerUsage;
import com.app.libraryproject.service.ComputerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping
    public ResponseEntity<List<StationStatusResponse>> getAllStations() {
        return ResponseEntity.ok(computerService.getAllStations());
    }

    @GetMapping("/queue")
    public ResponseEntity<List<QueueResponse>> getQueue() {
        return ResponseEntity.ok(computerService.getQueue());
    }

    @GetMapping("/history")
    public ResponseEntity<List<QueueResponse>> getHistory() {
        return ResponseEntity.ok(computerService.usageHistory());
    }

    @PostMapping("/queue")
    public ResponseEntity<QueueResponse> addToQueue(@Valid @RequestBody QueueRequest request) {
        ComputerUsage usage = computerService.addToQueue(request);
        return ResponseEntity.ok(QueueResponse.from(usage));
    }

    @PostMapping("/queue/cancel")
    public ResponseEntity<QueueResponse> cancelQueue(@RequestBody CancelQueueRequest request) {
        ComputerUsage cancel = computerService.cancelQueue(request);
        return ResponseEntity.ok(QueueResponse.from(cancel));
    }

    @PostMapping("/seat")
    public ResponseEntity<QueueResponse> seatUser(@RequestBody SeatUserRequest request) {
        ComputerUsage seat = computerService.seatUser(request);
        return ResponseEntity.ok(QueueResponse.from(seat));
    }

    @PostMapping("/finish")
    public ResponseEntity<QueueResponse> finishUsage(@RequestBody FinishUsageRequest request) {
        ComputerUsage finish = computerService.finishUsage(request);
        return ResponseEntity.ok(QueueResponse.from(finish));
    }

    @PostMapping("/create")
    public ResponseEntity<ComputerStationResponse> createStation(@RequestBody CreateComputerStationRequest request) {
        return ResponseEntity.ok(computerService.createStation(request));
    }
}

