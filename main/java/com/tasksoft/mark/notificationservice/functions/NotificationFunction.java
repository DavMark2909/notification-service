package com.tasksoft.mark.notificationservice.functions;

import com.tasksoft.mark.notificationservice.events.Notification;

import java.util.function.Function;

public class NotificationFunction {

    public Function<Notification, Long> saveNotification(){
        return recievedNotification -> {
//            save the notification to the database
            return null;
        };
    }
}
