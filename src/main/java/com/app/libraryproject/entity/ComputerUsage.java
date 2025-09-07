package com.app.libraryproject.entity;

import com.app.libraryproject.dto.computerUsage.QueueResponse;
import com.app.libraryproject.model.UsageStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ComputerUsage")
public class ComputerUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ComputerStation computerStation;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int declaredMinutes;

    @Enumerated(EnumType.STRING)
    private UsageStatus usageStatus;

    public QueueResponse toQueueResponse() {
        return QueueResponse.builder()
                .id(id)
                .member(member.toMemberResponse())
                .computerStation(computerStation.toComputerStationResponse())
                .startTime(startTime)
                .endTime(endTime)
                .declaredMinutes(declaredMinutes)
                .usageStatus(usageStatus)
                .build();
    }
}
