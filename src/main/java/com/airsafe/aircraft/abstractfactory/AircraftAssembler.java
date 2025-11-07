package com.airsafe.aircraft.abstractfactory;

import org.springframework.stereotype.Component;

import com.airsafe.aircraft.abstractfactory.components.Cockpit;
import com.airsafe.aircraft.abstractfactory.components.Engine;
import com.airsafe.aircraft.abstractfactory.components.SeatLayout;

@Component
public class AircraftAssembler {

    public AircraftConfiguration assemble(String model, AircraftManufacturer manufacturer) {
        AircraftFactory factory = switch (manufacturer) {
            case BOEING -> new BoeingFactory();
            case AIRBUS -> new AirbusFactory();
        };

        Engine engine = factory.createEngine();
        Cockpit cockpit = factory.createCockpit();
        SeatLayout seatLayout = factory.createSeatLayout();

        return new AircraftConfiguration(
                model,
                engine.description(),
                cockpit.layout(),
                seatLayout.arrangement()
        );
    }
}
