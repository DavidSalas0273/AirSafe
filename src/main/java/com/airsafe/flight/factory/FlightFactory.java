package com.airsafe.flight.factory;

import org.springframework.stereotype.Component;

@Component
public class FlightFactory {

    public Flight createFlight(FlightRequest request) {
        return switch (request.type()) {
            case DOMESTIC -> new DomesticFlight(
                    request.code(),
                    request.origin(),
                    request.destination(),
                    request.aircraftModel(),
                    request.basePrice()
            );
            case INTERNATIONAL -> new InternationalFlight(
                    request.code(),
                    request.origin(),
                    request.destination(),
                    request.aircraftModel(),
                    request.basePrice() * 1.25
            );
        };
    }
}
