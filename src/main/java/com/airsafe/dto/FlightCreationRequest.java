package com.airsafe.dto;

public record FlightCreationRequest(
        String type,
        String code,
        String origin,
        String destination,
        String aircraftModel,
        double basePrice
) {}
