package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.*;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public GetPersonListResponse getUserList(GetPersonListRequest request) {
        Pageable pageable = PageRequest.of(request.page(), request.limit());
        Page<User> users = userRepository.findAll(pageable);

        return new GetPersonListResponse(
                users.stream()
                        .map(User::toUserListItem)
                        .toList()
        );
    }
}
