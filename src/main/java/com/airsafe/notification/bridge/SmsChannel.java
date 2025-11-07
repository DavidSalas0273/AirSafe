package com.airsafe.notification.bridge;

public class SmsChannel implements NotificationChannel {
    @Override
    public String send(String recipient, String message) {
        return "SMS to %s :: %s".formatted(recipient, message);
    }
}
