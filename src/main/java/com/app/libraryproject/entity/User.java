package com.app.libraryproject.entity;

import com.app.libraryproject.dto.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate birthday;

    public GetUserListResponse.UserListItem toUserListItem() {
        return GetUserListResponse.UserListItem.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
    }
}