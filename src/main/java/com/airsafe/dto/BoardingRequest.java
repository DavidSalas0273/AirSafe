package com.airsafe.dto;

public record BoardingRequest(
        String flightType,
        String flightCode
) {}
