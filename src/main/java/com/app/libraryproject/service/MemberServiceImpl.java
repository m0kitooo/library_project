package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.*;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponse find(Long memberId) {
        Member member = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new RecordNotFoundException("Cannot find member with id" + memberId));

        return MemberResponse.from(member);
    }

    @Override
    public List<MemberResponse> findAll(
            Integer page,
            Integer limit,
            String filterFullName
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Member> members = memberRepository.searchMembersByFullName(filterFullName, pageable);

        return members
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    @Override
    public MemberResponse register(CreateMemberRequest request) {
        return memberRepository.save(request.toMember()).toMemberResponse();
    }
}
