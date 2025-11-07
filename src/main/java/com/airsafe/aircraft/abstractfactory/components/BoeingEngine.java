package com.airsafe.aircraft.abstractfactory.components;

public class BoeingEngine implements Engine {
    @Override
    public String description() {
        return "Boeing LEAP-1B twin-engine setup";
    }
}
