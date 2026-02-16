package com.tasksoft.mark.notificationservice.service;

import com.tasksoft.mark.notificationservice.entities.Notification;
import com.tasksoft.mark.notificationservice.entities.enums.NotificationType;
import com.tasksoft.mark.notificationservice.events.TaskNotificationDto;
import com.tasksoft.mark.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Long saveTaskNotification(TaskNotificationDto dto){
        Notification notification = new Notification();
        notification.setTaskId(dto.taskId());
        notification.setMessage(dto.message());
        if (dto.personal()){
            notification.setRecipientId(dto.recipientId());
            notification.setNotificationType(NotificationType.SINGLE);
        }
        else {
            notification.setGroupId(dto.recipientId());
            notification.setNotificationType(NotificationType.GROUP);
        }
        return notificationRepository.save(notification).getId();
    }
}
