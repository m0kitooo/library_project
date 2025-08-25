package com.app.libraryproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "computer_station_loan")
public class ComputerStationLoan {
    @Id
    private Long id;
    @OneToOne
    private ComputerStation computerStation;
    @OneToOne
    private Member member;
    private LocalDateTime start;
    private LocalDateTime end;
}
