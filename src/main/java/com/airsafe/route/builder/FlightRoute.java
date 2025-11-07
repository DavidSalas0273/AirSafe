package com.airsafe.route.builder;

import java.util.List;

public record FlightRoute(
        String flightCode,
        List<RouteLeg> legs,
        String departureGate,
        String arrivalGate,
        List<String> fuelStops
) {}
