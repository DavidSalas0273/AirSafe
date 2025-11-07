package com.airsafe.dto;

public record BookingResponse(
        String reservationId,
        String seat,
        String paymentConfirmation,
        String ticketDescription,
        double totalCost,
        String notificationMessage
) {}
