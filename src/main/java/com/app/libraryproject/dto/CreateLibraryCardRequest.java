package com.app.libraryproject.dto;

import com.app.libraryproject.entity.LibraryCard;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        CreateMemberRequest createMemberRequest
) {
    public LibraryCard toLibraryCard() {
        return LibraryCard
                .builder()
                .member(createMemberRequest.toMember())
                .expiryDate(LocalDate.now().plusYears(5))   //TODO wydaje mi sie ze lepiej usunac ta funckje poniewaz takie cos powinien robic serwis, ktos moze stworzyc obiekt wczesniej i go zmapowac co byloby problematyczne
                .build();
    }
}
