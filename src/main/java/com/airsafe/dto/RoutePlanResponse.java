package com.airsafe.dto;

import java.util.List;

import com.airsafe.route.builder.RouteLeg;

public record RoutePlanResponse(
        String flightCode,
        List<RouteLeg> legs,
        String departureGate,
        String arrivalGate,
        List<String> fuelStops
) {}
