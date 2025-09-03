package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.exception.ResourceConflictException;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.error.AppError;
import com.app.libraryproject.repository.LibraryCardRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.libraryproject.model.error.ErrorCode.MEMBER_NOT_FOUND;
import static com.app.libraryproject.model.error.ErrorCode.ACTIVE_LIBRARY_CARD_PER_USER_EXISTS;

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
        memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(MEMBER_NOT_FOUND, "Member does not exist with id: " + request.memberId())))
                .getLibraryCards()
                .stream()
                .filter(LibraryCard::isActive)
                .findFirst()
                .ifPresent(libraryCard -> {
                    throw new ResourceConflictException(new AppError(ACTIVE_LIBRARY_CARD_PER_USER_EXISTS, "Member already has an active library card"));
                });

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
                .findActiveCardByMemberId(memberId, LocalDate.now())
                .orElseThrow(() -> new RecordNotFoundException("There is no active library card for this member"))
                .toLibraryCardResponse();
    }

    @Override
    public List<LibraryCardResponse> getInactiveLibraryCardsByMemberId(Long memberId) {
        return libraryCardRepository.findInactiveCardsByMemberId(memberId, LocalDate.now())
                .stream()
                .map(LibraryCard::toLibraryCardResponse)
                .toList();
    }

}
