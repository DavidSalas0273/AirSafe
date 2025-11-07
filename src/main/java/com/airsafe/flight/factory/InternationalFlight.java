package com.airsafe.flight.factory;

public class InternationalFlight extends Flight {

    public InternationalFlight(String code, String origin, String destination, String aircraftModel, double basePrice) {
        super(code, origin, destination, aircraftModel, basePrice);
    }

    @Override
    public String policies() {
        return "International flight requires passport + visa verification";
    }
}
