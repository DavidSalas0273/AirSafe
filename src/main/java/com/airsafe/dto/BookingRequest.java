package com.airsafe.dto;

import java.util.List;

public record BookingRequest(
        String passengerName,
        String flightCode,
        String seatPreference,
        double basePrice,
        List<String> extras,
        String paymentMethod,
        String contactChannel,
        String contactValue
) {}
