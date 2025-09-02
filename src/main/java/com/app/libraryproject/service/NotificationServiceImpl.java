package com.app.libraryproject.service;

import com.app.libraryproject.dto.notification.NotificationDto;
import com.app.libraryproject.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.app.libraryproject.model.WorkerRole;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationDto> getNotifications(WorkerRole role, String username) {
        return notificationRepository.findByRoleAndUserUsername(role, username).stream()
                .map(n -> NotificationDto.builder()
                        .id(n.getId())
                        .content(n.getContent())
                        .username(n.getUser().getUsername())
                        .createdAt(n.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
