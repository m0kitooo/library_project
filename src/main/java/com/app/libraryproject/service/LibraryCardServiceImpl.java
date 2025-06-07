package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.repository.LibraryCardRepository;
import com.app.libraryproject.validate.CreateLibraryCardRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryCardServiceImpl implements LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;

    @Override
    public LibraryCardResponse register(CreateLibraryCardRequest createLibraryCardRequest) {
        return libraryCardRepository.save(
                LibraryCard
                        .builder()
                        .member(createLibraryCardRequest.createMemberRequest().toMember())
                        .expiryDate(LocalDate.now().plusYears(5))
                        .build()
        ).toDto();
    }
}
