package com.app.libraryproject.entity;

import com.app.libraryproject.dto.member.MemberResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Member extends AbstractPerson {

    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;

    public MemberResponse toMemberResponse() {
        return new MemberResponse(id, name, surname, nationalId, birthday);
    }
}