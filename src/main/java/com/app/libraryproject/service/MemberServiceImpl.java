package com.app.libraryproject.service;

import com.app.libraryproject.dto.MemberResponse;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponse registerMember(Member member) {
        return memberRepository.save(member).toMemberResponse();
    }
}
