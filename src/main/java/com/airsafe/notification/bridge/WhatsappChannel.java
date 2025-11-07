package com.airsafe.notification.bridge;

public class WhatsappChannel implements NotificationChannel {
    @Override
    public String send(String recipient, String message) {
        return "WHATSAPP to %s :: %s".formatted(recipient, message);
    }
}
