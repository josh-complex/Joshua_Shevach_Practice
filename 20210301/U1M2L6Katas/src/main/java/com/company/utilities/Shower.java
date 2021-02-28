package com.company.utilities;

import com.company.utilities.abstracts.TemperatureControlledUtility;

public class Shower extends TemperatureControlledUtility {

    public Shower() {
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        System.out.println("Shower is now running: " + active);
    }

    @Override
    public void setTemperature(float temperature){
        super.setTemperature(temperature);
        System.out.println("The shower temperature is now: " + temperature);
    }

}
