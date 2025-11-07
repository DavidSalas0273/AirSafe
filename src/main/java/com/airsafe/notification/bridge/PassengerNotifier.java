package com.airsafe.notification.bridge;

public class PassengerNotifier extends Notifier {

    public PassengerNotifier(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public String notify(String recipient, String message) {
        return channel.send(recipient, "Passenger update: " + message);
    }
}
