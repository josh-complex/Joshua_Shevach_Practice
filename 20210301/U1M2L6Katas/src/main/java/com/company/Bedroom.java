package com.company;

import com.company.abstracts.Room;
import com.company.enums.FlooringType;

public class Bedroom extends Room {

    public Bedroom() {
    }

    public Bedroom(double roomLength, double roomWidth, double ceilingHeight, FlooringType flooringType, int numLights) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.ceilingHeight = ceilingHeight;
        this.flooringType = flooringType;
        this.setLights(numLights);
    }

}
