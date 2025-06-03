package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate birthday;
}
