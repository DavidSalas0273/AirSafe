package com.airsafe.dto;

public record NotificationRequest(
        String channel,
        String recipient,
        String message
) {}
