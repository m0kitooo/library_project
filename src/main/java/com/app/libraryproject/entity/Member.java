package com.app.libraryproject.entity;

import com.app.libraryproject.dto.member.MemberResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member extends Person {

    @OneToMany(mappedBy = "member")
    private List<LibraryCard> libraryCard;

    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;

    public MemberResponse toMemberResponse() {
        return new MemberResponse(id, name, surname, nationalId, birthday);
    }
}