package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;

import java.util.List;

public interface MemberService {
    List<MemberResponse> findAll();
    MemberResponse register(CreateMemberRequest request);
//    GetLibraryCardDetailsResponse getPersonDetails(Long libraryCardId);
//    GetPersonListResponse getUserList(GetPersonListRequest request);
}
