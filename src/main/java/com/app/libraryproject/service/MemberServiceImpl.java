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
}
