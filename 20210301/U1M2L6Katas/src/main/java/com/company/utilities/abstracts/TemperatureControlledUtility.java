package com.company.utilities.abstracts;

public abstract class TemperatureControlledUtility extends Utility {

    protected float temperature;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

}
