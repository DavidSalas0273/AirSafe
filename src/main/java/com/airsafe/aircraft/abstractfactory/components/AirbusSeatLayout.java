package com.airsafe.aircraft.abstractfactory.components;

public class AirbusSeatLayout implements SeatLayout {
    @Override
    public String arrangement() {
        return "Airbus 3-3 single aisle with SpaceFlex cabin";
    }
}
