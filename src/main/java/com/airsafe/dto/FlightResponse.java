package com.airsafe.dto;

public record FlightResponse(
        String code,
        String origin,
        String destination,
        String aircraft,
        double price,
        String policies
) {}
