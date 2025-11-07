package com.airsafe.aircraft.abstractfactory.components;

public class BoeingCockpit implements Cockpit {
    @Override
    public String layout() {
        return "Boeing Sky Interior cockpit with dual HUD";
    }
}
