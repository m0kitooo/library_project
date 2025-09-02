package com.app.libraryproject.service;

import com.app.libraryproject.dto.user.CreateUserRequest;
import com.app.libraryproject.dto.user.GetPersonListRequest;
import com.app.libraryproject.dto.user.PersonResponse;
import com.app.libraryproject.dto.user.UserResponse;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getUserList(GetPersonListRequest request) {
        Pageable pageable = PageRequest.of(request.page(), request.limit());
        Page<User> users = userRepository.findAll(pageable);

        return users
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    @Override
    public void register(CreateUserRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent())
            throw new RuntimeException("User already exists");

        User user = request.toUser();
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }
}
