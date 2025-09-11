package com.app.libraryproject.dto.computerUsage;

import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.ComputerUsage;
import com.app.libraryproject.model.UsageStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QueueResponse(
        Long id,
        MemberResponse member,
        ComputerStationResponse computerStation,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startTime,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
