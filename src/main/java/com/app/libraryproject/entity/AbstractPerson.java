package com.app.libraryproject.entity;

import com.app.libraryproject.dto.user.GetPersonListResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractPerson {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String surname;

    @Column(nullable = false)
    protected LocalDate birthday;

    public GetPersonListResponse.PersonResponse toUserListItem() {
        return GetPersonListResponse.PersonResponse.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
    }
}
