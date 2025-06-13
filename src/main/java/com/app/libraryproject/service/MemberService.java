package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.user.GetPersonListRequest;
import com.app.libraryproject.dto.user.GetPersonListResponse;

public interface MemberService {
    GetLibraryCardDetailsResponse getPersonDetails(Long libraryCardId);
    GetPersonListResponse getUserList(GetPersonListRequest request);
}
