package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.Member;

import java.util.List;

public interface MemberService {
    List<MemberResponse> findAll();
    MemberResponse register(CreateMemberRequest request);
    Member getMemberByLibraryCardId(Long libraryCardId);
}
