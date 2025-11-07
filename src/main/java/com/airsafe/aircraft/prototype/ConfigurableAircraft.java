package com.airsafe.aircraft.prototype;

public class ConfigurableAircraft implements Cloneable {

    private String model;
    private int seatingCapacity;
    private int rangeKm;
    private String cabinConfiguration;
    private String variantName;

    public ConfigurableAircraft(String model, int seatingCapacity, int rangeKm, String cabinConfiguration) {
        this.model = model;
        this.seatingCapacity = seatingCapacity;
        this.rangeKm = rangeKm;
        this.cabinConfiguration = cabinConfiguration;
        this.variantName = model;
    }

    private ConfigurableAircraft(ConfigurableAircraft source) {
        this.model = source.model;
        this.seatingCapacity = source.seatingCapacity;
        this.rangeKm = source.rangeKm;
        this.cabinConfiguration = source.cabinConfiguration;
        this.variantName = source.variantName;
    }

    @Override
    public ConfigurableAircraft clone() {
        return new ConfigurableAircraft(this);
    }

    public String getModel() {
        return model;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public int getRangeKm() {
        return rangeKm;
    }

    public String getCabinConfiguration() {
        return cabinConfiguration;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public void setCabinConfiguration(String cabinConfiguration) {
        this.cabinConfiguration = cabinConfiguration;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
