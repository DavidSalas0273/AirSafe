package com.airsafe.service.seat;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class SeatAssignmentService {

    public String assignSeat(String preference) {
        char row = (char) ('A' + ThreadLocalRandom.current().nextInt(0, 6));
        int number = ThreadLocalRandom.current().nextInt(10, 40);
        return number + String.valueOf(row) + (preference == null ? "" : " (" + preference + ")");
    }
}
