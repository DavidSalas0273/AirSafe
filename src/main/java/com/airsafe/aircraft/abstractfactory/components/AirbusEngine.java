package com.airsafe.aircraft.abstractfactory.components;

public class AirbusEngine implements Engine {
    @Override
    public String description() {
        return "Airbus PW1100G geared turbofan";
    }
}
