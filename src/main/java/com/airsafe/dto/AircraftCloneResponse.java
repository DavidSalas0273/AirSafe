package com.airsafe.dto;

public record AircraftCloneResponse(
        String model,
        String variant,
        int seatingCapacity,
        int rangeKm,
        String cabinConfiguration
) {}
