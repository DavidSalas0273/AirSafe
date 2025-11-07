package com.airsafe.flight.factory;

public record FlightRequest(
        FlightType type,
        String code,
        String origin,
        String destination,
        String aircraftModel,
        double basePrice
) {}
