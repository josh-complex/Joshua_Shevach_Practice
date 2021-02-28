package com.company.abstracts;

import com.company.enums.FlooringType;
import com.company.utilities.Light;

public abstract class Room {

    protected boolean open;
    protected double roomLength;
    protected double roomWidth;
    protected double ceilingHeight;
    protected Light[] lights;
    protected FlooringType flooringType;

    public double getSquareFootage() {
        return roomLength * roomWidth;
    }

    public FlooringType getFlooringType() { return flooringType; }

    public void setFlooringType(FlooringType flooringType) {
        this.flooringType = flooringType;
    }

    public void setLights(int numLights){
        this.lights = new Light[numLights];
        for (int i = 0; i < numLights; i++)
            this.lights[i] = new Light();
    }

    public boolean toggleLightsActive() {
        if (this.lights.length > 0) {
            for (Light light : this.lights)
                light.setActive(!light.isActive());
            System.out.println("Lights on: " + this.lights[0].isActive());
            return this.lights[0].isActive();
        }
        System.out.println("There are no lights in here");
        return false;
    }

    public boolean toggleOpen() {
        this.open = !this.open;
        System.out.println("Room is now open: " + this.open);
        return this.open;
    }

}