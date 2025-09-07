package com.app.libraryproject.dto.computerUsage;

import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.ComputerUsage;
import com.app.libraryproject.model.StationStatus;
import lombok.Builder;

@Builder
public record StationStatusResponse(
        Long stationId,
        String stationName,
        StationStatus stationStatus,
        MemberResponse memberResponse,
        int estimatedWaitMinutes
) {
    public static StationStatusResponse from(ComputerUsage computerUsage) {
        return StationStatusResponse.builder()
                .stationId(computerUsage.getId())
                .stationName(computerUsage.getComputerStation().getName())
                .stationStatus(computerUsage.getComputerStation().getStatus())
                .memberResponse(computerUsage.getMember().toMemberResponse())
                .estimatedWaitMinutes(computerUsage.getDeclaredMinutes())
                .build();
    }
}
