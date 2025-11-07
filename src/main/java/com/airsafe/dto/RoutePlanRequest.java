package com.airsafe.dto;

import java.util.List;

public record RoutePlanRequest(
        String flightCode,
        List<RouteLegInput> legs,
        String departureGate,
        String arrivalGate,
        List<String> fuelStops,
        boolean useDirector
) {
    public record RouteLegInput(String origin, String destination, int durationMinutes) {}
}
