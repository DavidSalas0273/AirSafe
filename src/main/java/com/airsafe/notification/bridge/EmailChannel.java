package com.airsafe.notification.bridge;

public class EmailChannel implements NotificationChannel {
    @Override
    public String send(String recipient, String message) {
        return "EMAIL to %s :: %s".formatted(recipient, message);
    }
}
