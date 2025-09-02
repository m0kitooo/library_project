package com.app.libraryproject.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
}
