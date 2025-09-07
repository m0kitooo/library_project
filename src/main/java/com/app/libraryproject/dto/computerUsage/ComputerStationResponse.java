package com.app.libraryproject.dto.computerUsage;

import com.app.libraryproject.entity.ComputerStation;
import com.app.libraryproject.model.StationStatus;
import lombok.Builder;

@Builder
public record ComputerStationResponse(
        Long id,
        String name,
        StationStatus status
) {
    public static ComputerStationResponse from(ComputerStation station) {
        return ComputerStationResponse.builder()
                .id(station.getId())
                .name(station.getName())
                .status(station.getStatus())
                .build();
    }
}

