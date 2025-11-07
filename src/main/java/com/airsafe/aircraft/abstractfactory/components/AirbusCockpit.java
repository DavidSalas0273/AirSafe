package com.airsafe.aircraft.abstractfactory.components;

public class AirbusCockpit implements Cockpit {
    @Override
    public String layout() {
        return "Airbus fly-by-wire glass cockpit";
    }
}
