package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.GetPersonListRequest;
import com.app.libraryproject.dto.user.PersonResponse;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<PersonResponse> getUsers(
            Integer page,
            Integer limit,
            String filterFullName
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<User> users = userRepository.findAll(pageable);

        return users
                .stream()
                .map(PersonResponse::from)
                .toList();
    }
}
