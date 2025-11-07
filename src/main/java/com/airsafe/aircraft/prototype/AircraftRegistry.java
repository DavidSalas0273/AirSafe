package com.airsafe.aircraft.prototype;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AircraftRegistry {

    private final Map<String, ConfigurableAircraft> registry = new HashMap<>();

    public AircraftRegistry() {
        registry.put("B737", new ConfigurableAircraft("B737", 180, 5600, "Economy + 12 Business"));
        registry.put("A320", new ConfigurableAircraft("A320", 190, 6100, "Economy + 16 Business"));
    }

    public ConfigurableAircraft cloneForVariant(String model, String variantName, Integer newSeats, String cabinConfig) {
        ConfigurableAircraft prototype = registry.get(model);
        if (prototype == null) {
            throw new IllegalArgumentException("Unknown aircraft model: " + model);
        }
        ConfigurableAircraft clone = prototype.clone();
        clone.setVariantName(variantName);
        if (newSeats != null) {
            clone.setSeatingCapacity(newSeats);
        }
        if (cabinConfig != null) {
            clone.setCabinConfiguration(cabinConfig);
        }
        return clone;
    }
}
