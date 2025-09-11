package com.app.libraryproject.service;

import com.app.libraryproject.dto.computerUsage.*;
import com.app.libraryproject.entity.ComputerUsage;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ComputerService {
    List<StationStatusResponse> getAllStations();
    List<QueueResponse> getQueue();
    List<QueueResponse> usageHistory();
    ComputerUsage addToQueue(QueueRequest request);
    ComputerUsage cancelQueue(CancelQueueRequest request);
    ComputerUsage seatUser(SeatUserRequest request);
    @Transactional
    ComputerUsage finishUsage(FinishUsageRequest request);
    ComputerStationResponse createStation(CreateComputerStationRequest request);
}
