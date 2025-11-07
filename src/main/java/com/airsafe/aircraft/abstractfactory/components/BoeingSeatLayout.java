package com.airsafe.aircraft.abstractfactory.components;

public class BoeingSeatLayout implements SeatLayout {
    @Override
    public String arrangement() {
        return "Boeing 3-3 single aisle layout";
    }
}
