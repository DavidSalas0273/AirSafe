package com.airsafe.dto;

public record AircraftCloneRequest(
        String baseModel,
        String variant,
        Integer seatingCapacity,
        String cabinConfiguration
) {}
