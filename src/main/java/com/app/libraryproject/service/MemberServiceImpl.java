package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.user.*;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public GetLibraryCardDetailsResponse getPersonDetails(Long libraryCardId) {
        LibraryCard libraryCard = libraryCardRepository
                .findById(libraryCardId)
                .orElseThrow(
                        () -> new RecordNotFoundException("Record not found with id: " + libraryCardId)
                );

        return libraryCard.toLibraryCardDetails();
    }

    @Override
    public GetPersonListResponse getUserList(GetPersonListRequest request) {
        Pageable pageable = PageRequest.of(request.page(), request.limit());
        Page<Member> members = memberRepository.searchMembersByFullname(request.filterFullname(), pageable);

        return new GetPersonListResponse(
                members.stream()
                        .map(Member::toUserListItem)
                        .toList()
        );
    }
}
