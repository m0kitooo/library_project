package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.*;

public interface UserService {
    GetUserListResponse getUserList(GetUserListRequest request);
}
