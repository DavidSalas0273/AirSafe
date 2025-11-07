package com.airsafe.notification.bridge;

import org.springframework.stereotype.Component;

@Component
public class NotificationBridgeFactory {

    public Notifier build(String channelKey) {
        NotificationChannel channel = switch (channelKey.toUpperCase()) {
            case "SMS" -> new SmsChannel();
            case "WHATSAPP" -> new WhatsappChannel();
            default -> new EmailChannel();
        };
        return new PassengerNotifier(channel);
    }
}
