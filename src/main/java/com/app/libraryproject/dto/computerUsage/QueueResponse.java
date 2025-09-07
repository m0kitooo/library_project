package com.app.libraryproject.dto.computerUsage;

import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.ComputerUsage;
import com.app.libraryproject.model.UsageStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QueueResponse(
        Long id,
        MemberResponse member,
        ComputerStationResponse computerStation,
        LocalDateTime startTime,
        LocalDateTime endTime,
        int declaredMinutes,
        UsageStatus usageStatus
) {
    public static QueueResponse from(ComputerUsage computerUsage) {
        return QueueResponse.builder()
                .id(computerUsage.getId())
                .member(computerUsage.getMember().toMemberResponse())
                .computerStation(computerUsage.getComputerStation().toComputerStationResponse())
                .startTime(computerUsage.getStartTime())
                .endTime(computerUsage.getEndTime())
                .declaredMinutes(computerUsage.getDeclaredMinutes())
                .usageStatus(computerUsage.getUsageStatus())
                .build();
    }
}
