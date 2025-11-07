package com.airsafe.notification.bridge;

public interface NotificationChannel {
    String send(String recipient, String message);
}
