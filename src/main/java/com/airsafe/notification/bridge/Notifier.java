package com.airsafe.notification.bridge;

public abstract class Notifier {

    protected final NotificationChannel channel;

    protected Notifier(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract String notify(String recipient, String message);
}
