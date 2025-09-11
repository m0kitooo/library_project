package com.app.libraryproject.service;

import com.app.libraryproject.dto.computerUsage.*;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.ComputerStation;
import com.app.libraryproject.entity.ComputerUsage;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.model.StationStatus;
import com.app.libraryproject.model.UsageStatus;
import com.app.libraryproject.repository.ComputerStationRepository;
import com.app.libraryproject.repository.ComputerUsageRepository;
import com.app.libraryproject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {

    private final ComputerStationRepository stationRepository;
    private final ComputerUsageRepository usageRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<StationStatusResponse> getAllStations() {
        List<ComputerStation> stations = stationRepository.findAll();
        List<StationStatusResponse> responses = new ArrayList<>();

        stations.forEach(station -> {
            Optional<ComputerUsage> activeUsage = usageRepository.findByComputerStationAndUsageStatus(station, UsageStatus.ACTIVE)
                    .stream()
                    .findFirst();

            MemberResponse currentMember = activeUsage.map(u -> MemberResponse.from(u.getMember()))
                    .orElse(null);

            int estimatedWait = usageRepository.findByUsageStatus(UsageStatus.WAITING).stream()
                    .mapToInt(ComputerUsage::getDeclaredMinutes)
                    .sum();

            responses.add(new StationStatusResponse(
                    station.getId(),
                    station.getName(),
                    station.getStatus(),
                    currentMember,
                    estimatedWait
            ));
        });

        return responses;
    }

    @Override
    public List<QueueResponse> getQueue(){
        List<QueueResponse> responses = new ArrayList<>();
        List<ComputerUsage> queue = new ArrayList<>();
        queue.addAll(usageRepository.findByUsageStatus(UsageStatus.WAITING));
        queue.addAll(usageRepository.findByUsageStatus(UsageStatus.ACTIVE));

        return getQueueResponses(responses, queue);
    }

    @Override
    public List<QueueResponse> usageHistory(){
        List<QueueResponse> responses = new ArrayList<>();
        List<ComputerUsage> queue = usageRepository.findByUsageStatus(UsageStatus.FINISHED);

        return getQueueResponses(responses, queue);
    }

    @Override
    public ComputerUsage addToQueue(QueueRequest request) {
        Member member = memberRepository.findByLibraryCards_Id(Long.valueOf(request.libraryCardNumber()))
                .orElseThrow(() -> new RuntimeException("Nie znaleziono członka o podanej karcie"));

        ComputerUsage usage = ComputerUsage.builder()
                .member(member)
                .declaredMinutes(request.declaredMinutes())
                .usageStatus(UsageStatus.WAITING)
                .build();

        return usageRepository.save(usage);
    }

    @Override
    public ComputerUsage cancelQueue(CancelQueueRequest request) {
        ComputerUsage usage = usageRepository.findById(request.usageId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono wpisu w kolejce"));

        if (usage.getUsageStatus() != UsageStatus.WAITING)
            throw new RuntimeException("Można anulować tylko osoby w kolejce");

        usage.setUsageStatus(UsageStatus.FINISHED);
        return usageRepository.save(usage);
    }

    @Transactional
    @Override
    public ComputerUsage seatUser(SeatUserRequest request) {
        ComputerUsage usage = usageRepository.findById(request.usageId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono osoby w kolejce"));

        if (usage.getUsageStatus() != UsageStatus.WAITING)
            throw new RuntimeException("Tylko osoby w kolejce można posadzić");

        ComputerStation station = stationRepository.findById(request.stationId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono stanowiska"));

        if (station.getStatus() != StationStatus.FREE) throw new RuntimeException("Stanowisko jest zajęte");

        usage.setUsageStatus(UsageStatus.ACTIVE);
        usage.setStartTime(LocalDateTime.now());
        usage.setComputerStation(station);

        station.setStatus(StationStatus.OCCUPIED);
        stationRepository.save(station);

        return usageRepository.save(usage);
    }

    @Transactional
    @Override
    public ComputerUsage finishUsage(FinishUsageRequest request) {
        ComputerUsage usage = usageRepository.findById(request.usageId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono wpisu"));

        if (usage.getUsageStatus() != UsageStatus.ACTIVE)
            throw new RuntimeException("Można zakończyć tylko aktywne korzystanie");

        usage.setUsageStatus(UsageStatus.FINISHED);
        usage.setEndTime(LocalDateTime.now());

        ComputerStation station = usage.getComputerStation();
        station.setStatus(StationStatus.FREE);
        stationRepository.save(station);

        return usageRepository.save(usage);
    }

    @Override
    public ComputerStationResponse createStation(CreateComputerStationRequest request) {
        ComputerStation station = request.toEntity();
        stationRepository.save(station);
        return ComputerStationResponse.from(station);
    }

    private List<QueueResponse> getQueueResponses(List<QueueResponse> responses, List<ComputerUsage> queue) {
        queue.forEach(usage -> {
            MemberResponse currentMember = MemberResponse.from(usage.getMember());
            ComputerStationResponse currentComputerStation = usage.getComputerStation() != null
                    ? ComputerStationResponse.from(usage.getComputerStation())
                    : null;

            responses.add(new QueueResponse(
                    usage.getId(),
                    currentMember,
                    currentComputerStation,
                    usage.getStartTime(),
                    usage.getEndTime(),
                    usage.getDeclaredMinutes(),
                    usage.getUsageStatus()
            ));
        });

        return responses;
    }
}

