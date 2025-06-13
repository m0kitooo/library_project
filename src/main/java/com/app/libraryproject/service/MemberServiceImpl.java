package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

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

//    @Override
//    public GetLibraryCardDetailsResponse getPersonDetails(Long libraryCardId) {
//        LibraryCard libraryCard = libraryCardRepository
//                .findById(libraryCardId)
//                .orElseThrow(
//                        () -> new RecordNotFoundException("Record not found with id: " + libraryCardId)
//                );
//
//        return libraryCard.toLibraryCardDetails();
//    }
//
//    @Override
//    public GetPersonListResponse getUserList(GetPersonListRequest request) {
//        Pageable pageable = PageRequest.of(request.page(), request.limit());
//        Page<Member> members = memberRepository.searchMembersByFullname(request.filterFullname(), pageable);
//
//        return new GetPersonListResponse(
//                members.stream()
//                        .map(Member::toUserListItem)
//                        .toList()
//        );
//    }
}
