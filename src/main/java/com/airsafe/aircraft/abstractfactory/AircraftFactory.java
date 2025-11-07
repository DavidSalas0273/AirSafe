package com.airsafe.aircraft.abstractfactory;

import com.airsafe.aircraft.abstractfactory.components.Cockpit;
import com.airsafe.aircraft.abstractfactory.components.Engine;
import com.airsafe.aircraft.abstractfactory.components.SeatLayout;

public interface AircraftFactory {
    Engine createEngine();
    Cockpit createCockpit();
    SeatLayout createSeatLayout();
}
