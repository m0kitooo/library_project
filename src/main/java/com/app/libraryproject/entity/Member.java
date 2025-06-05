package com.app.libraryproject.entity;

import com.app.libraryproject.dto.MemberResponse;
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

    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;

    @Column(nullable = false)
    private LocalDate birthday;

    public MemberResponse toMemberResponse() {
        return new MemberResponse(id, name, surname, nationalId, birthday);
    }
}
