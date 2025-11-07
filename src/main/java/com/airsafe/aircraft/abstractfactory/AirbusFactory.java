package com.airsafe.aircraft.abstractfactory;

import com.airsafe.aircraft.abstractfactory.components.AirbusCockpit;
import com.airsafe.aircraft.abstractfactory.components.AirbusEngine;
import com.airsafe.aircraft.abstractfactory.components.AirbusSeatLayout;
import com.airsafe.aircraft.abstractfactory.components.Cockpit;
import com.airsafe.aircraft.abstractfactory.components.Engine;
import com.airsafe.aircraft.abstractfactory.components.SeatLayout;

public class AirbusFactory implements AircraftFactory {
    @Override
    public Engine createEngine() {
        return new AirbusEngine();
    }

    @Override
    public Cockpit createCockpit() {
        return new AirbusCockpit();
    }

    @Override
    public SeatLayout createSeatLayout() {
        return new AirbusSeatLayout();
    }
}
