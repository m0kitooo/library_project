package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;

import java.util.List;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.user.GetPersonListRequest;
import com.app.libraryproject.dto.user.GetPersonListResponse;

public interface MemberService {
    List<MemberResponse> findAll();
    MemberResponse register(CreateMemberRequest request);
//    GetLibraryCardDetailsResponse getPersonDetails(Long libraryCardId);
//    GetPersonListResponse getUserList(GetPersonListRequest request);
}
