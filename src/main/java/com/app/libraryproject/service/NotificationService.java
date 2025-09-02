package com.app.libraryproject.service;


import com.app.libraryproject.dto.notification.NotificationDto;
import com.app.libraryproject.model.WorkerRole;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getNotifications(WorkerRole role, String username);
}