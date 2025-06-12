package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.GetUserListRequest;
import com.app.libraryproject.dto.user.GetUserListResponse;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public GetUserListResponse getUserList(GetUserListRequest request) {
        Pageable pageable = PageRequest.of(request.page(), request.limit());
        Page<User> users = userRepository.findAll(pageable);

        return new GetUserListResponse(
                users.stream()
                        .map(User::toUserListItem)
                        .toList()
        );
    }
}
