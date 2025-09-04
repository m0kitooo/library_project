package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Notification;
import com.app.libraryproject.model.NotificationStatus;
import com.app.libraryproject.model.WorkerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRoleAndUserUsername(WorkerRole role, String username);
}
