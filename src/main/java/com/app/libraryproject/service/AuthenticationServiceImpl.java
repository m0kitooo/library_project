package com.app.libraryproject.service;

import com.app.libraryproject.entity.User;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.model.UserPrincipal;
import com.app.libraryproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("User not authenticated");
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return userRepository
                .findByUsername(userPrincipal.getUsername())
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
    }
}
