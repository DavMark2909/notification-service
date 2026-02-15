package com.tasksoft.mark.notificationservice.functions;

import com.tasksoft.mark.notificationservice.events.NotificationDto;

import java.util.function.Function;

public class NotificationFunction {

    public Function<NotificationDto, Long> saveNotification(){
        return recievedNotification -> {
//            save the notification to the database
            return null;
        };
    }
}
