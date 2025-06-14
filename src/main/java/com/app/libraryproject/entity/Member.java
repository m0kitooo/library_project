package com.app.libraryproject.entity;

import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
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
public class Member extends AbstractPerson {

//    @OneToMany(mappedBy = "member")
//    private List<LibraryCard> libraryCard;

    @OneToOne
    @JoinColumn(name = "library_card_id")
    private LibraryCard libraryCard;

    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;

    public MemberResponse toMemberResponse() {
        return new MemberResponse(id, name, surname, nationalId, birthday, LibraryCardResponse.from(libraryCard));
    }
}