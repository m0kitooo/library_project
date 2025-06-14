package com.app.libraryproject.service;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.dto.user.GetPersonListRequest;

import java.util.List;

public interface MemberService {
    List<MemberResponse> findAll(
            Integer page,
            Integer limit,
            String filterFullName
    );
    MemberResponse register(CreateMemberRequest request);
    MemberResponse find(Long id);
//    GetLibraryCardDetailsResponse getPersonDetails(Long libraryCardId);
//    GetPersonListResponse getUserList(GetPersonListRequest request);
}
