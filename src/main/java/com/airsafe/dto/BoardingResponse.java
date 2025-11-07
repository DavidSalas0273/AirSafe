package com.airsafe.dto;

import java.util.List;

public record BoardingResponse(String flightCode, List<String> steps) {}
