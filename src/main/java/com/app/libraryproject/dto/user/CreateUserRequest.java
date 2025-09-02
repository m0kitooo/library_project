package com.app.libraryproject.dto.user;

import com.app.libraryproject.entity.User;
import com.app.libraryproject.model.WorkerRole;
import org.hibernate.jdbc.Work;

public record CreateUserRequest(
        String username,
        String password,
        WorkerRole role
) {
    public User toUser() {
        return User
                .builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }
}
