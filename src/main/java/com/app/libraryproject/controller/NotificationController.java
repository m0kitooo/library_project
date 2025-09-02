package com.app.libraryproject.controller;

import com.app.libraryproject.dto.notification.NotificationDto;
import com.app.libraryproject.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.libraryproject.model.WorkerRole;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotifications(
            @RequestParam WorkerRole role,
            @RequestParam String username
    ) {
        return ResponseEntity.ok(notificationService.getNotifications(role, username));
    }
}
