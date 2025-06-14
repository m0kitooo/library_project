package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.*;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryCardServiceImpl implements LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long registerLibraryCard(CreateLibraryCardRequest request) {
        Member member = memberRepository
                .findById(request.memberId())
                .orElseThrow(() -> new RecordNotFoundException("Member not found"));

        LibraryCard libraryCard = LibraryCard
                .builder()
                .member(member)
                .creationDate(LocalDate.now())
                .expiryDate(request.expiryDate())
                .build();

        member.setLibraryCard(libraryCard);
        memberRepository.save(member);

        return libraryCardRepository
                .save(libraryCard)
                .getId();
    }

    @Override
    public LibraryCardResponse getLibraryCardDetails(Long libraryCardId) {
        LibraryCard libraryCard = libraryCardRepository
                .findById(libraryCardId)
                .orElseThrow(
                        () -> new RecordNotFoundException("Record not found with id: " + libraryCardId)
                );

        return LibraryCardResponse.from(libraryCard);
    }
}