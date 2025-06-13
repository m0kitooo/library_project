package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.*;

public interface UserService {
    GetPersonListResponse getUserList(GetPersonListRequest request);
}
