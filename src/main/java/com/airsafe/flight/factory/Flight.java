package com.airsafe.flight.factory;

public abstract class Flight {

    private final String code;
    private final String origin;
    private final String destination;
    private final String aircraftModel;
    private final double basePrice;

    protected Flight(String code, String origin, String destination, String aircraftModel, double basePrice) {
        this.code = code;
        this.origin = origin;
        this.destination = destination;
        this.aircraftModel = aircraftModel;
        this.basePrice = basePrice;
    }

    public String getCode() {
        return code;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public abstract String policies();
}
