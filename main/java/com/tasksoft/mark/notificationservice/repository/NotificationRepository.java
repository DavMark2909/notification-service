package com.tasksoft.mark.notificationservice.repository;

import com.tasksoft.mark.notificationservice.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
