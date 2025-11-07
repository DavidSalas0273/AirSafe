package com.airsafe.service.payment;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String charge(String method, double amount) {
        return method.toUpperCase() + "-TXN-" + Instant.now().toEpochMilli();
    }
}
