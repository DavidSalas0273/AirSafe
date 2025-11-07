package com.airsafe.route.builder;

import org.springframework.stereotype.Component;

@Component
public class FlightRouteDirector {

    public FlightRoute buildExpressRoute(FlightRouteBuilder builder, String flightCode, String origin, String destination) {
        builder.reset();
        return builder.forFlight(flightCode)
                .addLeg(origin, destination, 180)
                .departureGate("A5")
                .arrivalGate("B12")
                .build();
    }
}
