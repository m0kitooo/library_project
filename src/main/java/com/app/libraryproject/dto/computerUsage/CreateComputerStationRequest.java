package com.app.libraryproject.dto.computerUsage;

import com.app.libraryproject.entity.ComputerStation;
import com.app.libraryproject.model.StationStatus;
import lombok.Builder;

@Builder
public record CreateComputerStationRequest(
        String name
) {
    public ComputerStation toEntity() {
        return ComputerStation
                .builder()
                .name(name)
                .status(StationStatus.FREE)
                .build();
    }
}

