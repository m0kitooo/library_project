package com.app.libraryproject.entity;

import com.app.libraryproject.dto.computerUsage.ComputerStationResponse;
import com.app.libraryproject.model.StationStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "computer_station")
public class ComputerStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private StationStatus status;

    public ComputerStationResponse toComputerStationResponse() {
        return ComputerStationResponse.builder()
                .id(id)
                .name(name)
                .status(status)
                .build();
    }
}
