package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.ResourceConflictException;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.error.AppError;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.app.libraryproject.model.error.ErrorCode.MEMBER_NOT_FOUND;
import static com.app.libraryproject.model.error.ErrorCode.MEMBER_PESEL_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponse findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        new AppError(MEMBER_NOT_FOUND, "Member not found with id: " + id)
                ))
                .toMemberResponse();
    }

    @Override
    public List<MemberResponse> findAll() {
        return memberRepository
                .findAll()
                .stream()
                .map(Member::toMemberResponse)
                .toList();
    }

    @Override
    public List<MemberResponse> findByPhrase(String phrase) {
        return memberRepository
                .findByPhrase(phrase)
                .stream()
                .map(Member::toMemberResponse)
                .toList();
    }

    @Override
    public MemberResponse register(CreateMemberRequest request) {
        if (memberRepository.existsByPesel(request.pesel()))
            throw new ResourceConflictException(
                    new AppError(MEMBER_PESEL_ALREADY_EXISTS,
                    "Member with pesel: " + request.pesel() + " already exists")
            );
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
