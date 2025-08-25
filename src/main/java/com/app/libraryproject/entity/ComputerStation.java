package com.app.libraryproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    @Builder.Default
    private boolean decommissioned = false;
}
