package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.LibraryCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryCardServiceImpl implements LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;

    @Override
    @Transactional
    public LibraryCardResponse registerLibraryCard(CreateLibraryCardRequest request) {
        return libraryCardRepository.save(
                LibraryCard
                        .builder()
                        .member(request.createMemberRequest().toMember())
                        .creationDate(LocalDate.now())
                        .expiryDate(LocalDate.now().plusYears(5))
                        .build()
        ).toLibraryCardResponse();
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

    @Override
    public LibraryCard getLibraryCardById(Long libraryCardId) {
        return libraryCardRepository.findById(libraryCardId).orElseThrow();
    }
}
