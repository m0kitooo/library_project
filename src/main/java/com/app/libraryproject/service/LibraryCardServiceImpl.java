package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.LibraryCardRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryCardServiceImpl implements LibraryCardService {
    private final MemberRepository memberRepository;
    private final LibraryCardRepository libraryCardRepository;

    @Override
    @Transactional
    public LibraryCardResponse registerLibraryCard(CreateLibraryCardRequest request) {
        return libraryCardRepository.save(
                LibraryCard
                        .builder()
                        .member(
                                memberRepository
                                        .findMemberById(request.memberId())
                                        .orElseThrow(() -> new RecordNotFoundException("Member does not exist"))
                        )
                        .creationDate(LocalDate.now())
                        .expiryDate(LocalDate.now().plusYears(5))
                        .build()
        ).toLibraryCardResponse();
    }

    @Override
    public GetLibraryCardDetailsResponse getLibraryCardDetails(Long libraryCardId) {
        return libraryCardRepository
                .findById(libraryCardId)
                .orElseThrow(() -> new RecordNotFoundException("Record not found with id: " + libraryCardId))
                .toLibraryCardDetails();
    }

    @Override
    public LibraryCardResponse getActiveLibraryCardByMemberId(Long memberId) {
        return libraryCardRepository
                .findActiveCardByMemberId(memberId)
                .orElseThrow(() -> new RecordNotFoundException("There is no library card with provided member id"))
                .toLibraryCardResponse();
    }
}
