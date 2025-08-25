package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.*;

import java.util.List;

public interface UserService {
    List<PersonResponse> getUserList(GetPersonListRequest request);
    void register(CreateUserRequest request);
}
