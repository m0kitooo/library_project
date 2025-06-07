package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.LibraryCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryCardServiceImpl implements LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;

    @Override
    public void registerLibraryCard(CreateLibraryCardRequest createLibraryCardRequest) {
        libraryCardRepository.save(
                LibraryCard
                        .builder()
                        .member(createLibraryCardRequest.createMemberRequest().toMember())
                        .expiryDate(LocalDate.now().plusYears(5))
                        .build()
        ).toLibraryCardDetails();
    }

    @Override
    public GetLibraryCardDetailsResponse getLibraryCardDetails(Long libraryCardId) {
        LibraryCard libraryCard = libraryCardRepository
                .findById(libraryCardId)
                .orElseThrow(
                        () -> new RecordNotFoundException("Record not found with id: " + libraryCardId)
                );

        return libraryCard.toLibraryCardDetails();
    }
}
