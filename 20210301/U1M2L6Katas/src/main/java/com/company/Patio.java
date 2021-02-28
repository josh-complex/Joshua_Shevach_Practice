package com.company;

import com.company.abstracts.Room;
import com.company.enums.FlooringType;

public class Patio extends Room {

    public Patio() {
    }

    public Patio(double roomLength, double roomWidth, double ceilingHeight, FlooringType flooringType, int numLights) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.ceilingHeight = ceilingHeight;
        this.flooringType = flooringType;
        this.setLights(numLights);
    }

}
