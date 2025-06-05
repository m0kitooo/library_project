package com.app.libraryproject.service;

import com.app.libraryproject.dto.MemberResponse;
import com.app.libraryproject.entity.Member;

public interface MemberService {
    MemberResponse registerMember(Member member);
}
