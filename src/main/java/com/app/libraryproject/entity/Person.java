package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Person {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String surname;

    @Column(nullable = false)

//    @Check(constraints = "CURRENT_DATE - birthday >= INTERVAL '13 years'")
    protected LocalDate birthday;
}
