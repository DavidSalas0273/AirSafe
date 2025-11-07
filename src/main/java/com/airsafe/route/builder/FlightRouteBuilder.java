package com.airsafe.route.builder;

import java.util.ArrayList;
import java.util.List;

public class FlightRouteBuilder {

    private String flightCode;
    private final List<RouteLeg> legs = new ArrayList<>();
    private String departureGate;
    private String arrivalGate;
    private final List<String> fuelStops = new ArrayList<>();

    public FlightRouteBuilder forFlight(String flightCode) {
        this.flightCode = flightCode;
        return this;
    }

    public FlightRouteBuilder addLeg(String origin, String destination, int durationMinutes) {
        this.legs.add(new RouteLeg(origin, destination, durationMinutes));
        return this;
    }

    public FlightRouteBuilder departureGate(String departureGate) {
        this.departureGate = departureGate;
        return this;
    }

    public FlightRouteBuilder arrivalGate(String arrivalGate) {
        this.arrivalGate = arrivalGate;
        return this;
    }

    public FlightRouteBuilder addFuelStop(String fuelStop) {
        this.fuelStops.add(fuelStop);
        return this;
    }

    public FlightRoute build() {
        return new FlightRoute(flightCode, List.copyOf(legs), departureGate, arrivalGate, List.copyOf(fuelStops));
    }

    public void reset() {
        this.flightCode = null;
        this.legs.clear();
        this.departureGate = null;
        this.arrivalGate = null;
        this.fuelStops.clear();
    }
}
