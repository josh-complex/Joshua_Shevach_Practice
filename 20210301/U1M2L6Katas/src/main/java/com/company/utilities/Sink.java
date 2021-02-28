package com.company.utilities;

import com.company.utilities.abstracts.TemperatureControlledUtility;

public class Sink extends TemperatureControlledUtility {

    public Sink() {
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        System.out.println("Sink is now running: " + active);
    }

    @Override
    public void setTemperature(float temperature){
        super.setTemperature(temperature);
        System.out.println("The sink temperature is now: " + temperature);
    }

}
