package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.repository.LibraryCardRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final LibraryCardRepository libraryCardRepository;

    @Override
    public List<MemberResponse> findAll() {
        return memberRepository
                .findAll()
                .stream()
                .map(Member::toMemberResponse)
                .toList();
    }

    @Override
    public MemberResponse register(CreateMemberRequest request) {
        return memberRepository.save(request.toMember()).toMemberResponse();
    }

    @Override
    public List<MemberResponse> findByLibraryCardId(Long cardId) {
        Optional<LibraryCard> cardOptional = libraryCardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            Member member = cardOptional.get().getMember();
            return List.of(member.toMemberResponse());
        }
        return Collections.emptyList();
    }
}