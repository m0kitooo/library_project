package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.*;

import java.util.List;

public interface UserService {
    List<PersonResponse> getUsers(
            Integer page,
            Integer limit,
            String filterFullName
    );
}
