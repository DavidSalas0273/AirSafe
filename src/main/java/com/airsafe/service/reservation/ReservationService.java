package com.airsafe.service.reservation;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    public String reserve(String passengerName, String flightCode) {
        String safeName = passengerName == null ? "GUEST" : passengerName;
        return "RSV-" + safeName.replaceAll("\\s+", "").toUpperCase() + "-" + flightCode + "-" + UUID.randomUUID().toString().substring(0, 6);
    }
}
