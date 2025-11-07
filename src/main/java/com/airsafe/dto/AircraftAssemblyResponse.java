package com.airsafe.dto;

public record AircraftAssemblyResponse(
        String model,
        String engine,
        String cockpit,
        String seatLayout
) {}
