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
    private List<LibraryCard> libraryCards;

    @Column(name = "pesel", nullable = false, unique = true, length = 11)
    private String pesel;

    @Column(nullable = false)
    private String address;

    public MemberResponse toMemberResponse() {
        return new MemberResponse(id, name, surname, pesel, birthday);
    }
}