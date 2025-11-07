package com.airsafe.aircraft.abstractfactory;

import com.airsafe.aircraft.abstractfactory.components.BoeingCockpit;
import com.airsafe.aircraft.abstractfactory.components.BoeingEngine;
import com.airsafe.aircraft.abstractfactory.components.BoeingSeatLayout;
import com.airsafe.aircraft.abstractfactory.components.Cockpit;
import com.airsafe.aircraft.abstractfactory.components.Engine;
import com.airsafe.aircraft.abstractfactory.components.SeatLayout;

public class BoeingFactory implements AircraftFactory {
    @Override
    public Engine createEngine() {
        return new BoeingEngine();
    }

    @Override
    public Cockpit createCockpit() {
        return new BoeingCockpit();
    }

    @Override
    public SeatLayout createSeatLayout() {
        return new BoeingSeatLayout();
    }
}
