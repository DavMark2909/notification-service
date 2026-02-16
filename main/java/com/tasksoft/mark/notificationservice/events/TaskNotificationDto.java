package com.tasksoft.mark.notificationservice.events;

public record TaskNotificationDto(
        Long taskId,
        Long recipientId,
        boolean personal,
        String message

)
{}
