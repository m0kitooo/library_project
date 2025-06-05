package com.app.libraryproject.service;

import com.app.libraryproject.dto.*;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryCardService implements LibraryCardServiceInterface {

    private final LibraryCardRepository libraryCardRepository;
    private final MemberRepository memberRepository;

    @Override
    public LibraryCard registerCard(CreateLibraryCardRequest request) {

        Optional<Member> member = memberRepository.findByFullName(request.name(), request.surname());
        LibraryCard libraryCard = new LibraryCard();

        if (member.isEmpty()) {
            Member newMember = new Member();
            newMember.setName(request.name());
            newMember.setSurname(request.surname());
            libraryCard.setMember(newMember);
        } else {
            libraryCard.setMember(member.get());
        }

        return libraryCardRepository.save(libraryCard);
    }
}