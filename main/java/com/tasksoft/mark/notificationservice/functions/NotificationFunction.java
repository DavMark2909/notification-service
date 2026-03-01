package com.tasksoft.mark.notificationservice.functions;

import com.tasksoft.mark.notificationservice.events.TaskNotificationDto;
import com.tasksoft.mark.notificationservice.service.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class NotificationFunction {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;


    public NotificationFunction(NotificationService notificationService, SimpMessagingTemplate messagingTemplate) {
        this.notificationService = notificationService;
        this.messagingTemplate = messagingTemplate;
    }

    @Bean
    public Consumer<TaskNotificationDto> saveNotification(){
        return receivedNotification -> {
            System.out.println("Received the message: " + receivedNotification.message());
            notificationService.saveTaskNotification(receivedNotification);
            if (receivedNotification.personal()){
                messagingTemplate.convertAndSendToUser(
                        String.valueOf(receivedNotification.recipientId()),
                        "/queue/notifications",
                        receivedNotification
                );
            } else {
                String groupTopic = "/topic/group." + receivedNotification.recipientId();
                messagingTemplate.convertAndSend(groupTopic, receivedNotification);
            }

        };
    }
}
