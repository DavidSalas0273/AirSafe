package com.airsafe.flight.factory;

public class DomesticFlight extends Flight {

    public DomesticFlight(String code, String origin, String destination, String aircraftModel, double basePrice) {
        super(code, origin, destination, aircraftModel, basePrice);
    }

    @Override
    public String policies() {
        return "Domestic baggage allowance: 1 cabin bag + 1 checked bag";
    }
}
