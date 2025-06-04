package com.app.libraryproject.mapper;

import com.app.libraryproject.dto.CreateLibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LibraryCardMapper {

    @Mapping(source = "member.name", target = "name")
    @Mapping(source = "member.surname", target = "surname")
    CreateLibraryCardResponse toResponse(LibraryCard libraryCard);
}
