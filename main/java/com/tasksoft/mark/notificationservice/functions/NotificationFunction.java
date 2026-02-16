package com.tasksoft.mark.notificationservice.functions;

import com.tasksoft.mark.notificationservice.events.TaskNotificationDto;
import com.tasksoft.mark.notificationservice.service.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class NotificationFunction {

    private NotificationService notificationService;

    public NotificationFunction(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Bean
    public Consumer<TaskNotificationDto> saveNotification(){
        return receivedNotification -> {
            System.out.println("Received the message: " + receivedNotification.message());
            notificationService.saveTaskNotification(receivedNotification);
        };
    }
}
