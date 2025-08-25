package com.app.libraryproject.service;

import com.app.libraryproject.entity.ComputerStationQueue;

import java.util.List;

public interface ComputerStationQueueService {
    ComputerStationQueue addMemberToQueue(Long computerStationId, Long memberId, int exceptesUsingHours);
    void removeMemberFromQueue(Long computerStationQueueId);
    List<ComputerStationQueue> getComputerStationQueue(Long computerStationId);
}
