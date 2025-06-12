package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.GetUserListRequest;
import com.app.libraryproject.dto.user.GetUserListResponse;

public interface UserService {
    GetUserListResponse getUserList(GetUserListRequest request);
}
