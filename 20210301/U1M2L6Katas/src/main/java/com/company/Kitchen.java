package com.company;

import com.company.abstracts.Room;
import com.company.enums.FlooringType;
import com.company.utilities.*;

public class Kitchen extends Room {

    private Refrigerator refrigerator;
    private Sink sink;

    public Kitchen() {
    }

    public Kitchen(Refrigerator refrigerator, Sink sink, double roomLength, double roomWidth, double ceilingHeight, FlooringType flooringType, int numLights) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.ceilingHeight = ceilingHeight;
        this.flooringType = flooringType;
        this.refrigerator = refrigerator;
        this.sink = sink;
        this.setLights(numLights);
    }

    public double getRoomLength() {
        return roomLength;
    }

    public void setRoomLength(double roomLength) {
        this.roomLength = roomLength;
    }

    public double getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(double roomWidth) {
        this.roomWidth = roomWidth;
    }

    public double getCeilingHeight() {
        return ceilingHeight;
    }

    public void setCeilingHeight(double ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }

    public Sink getSink() {
        return sink;
    }

    public void setSink(Sink sink) {
        this.sink = sink;
    }

    public boolean toggleRefrigeratorActive() {
        refrigerator.setActive(!refrigerator.isActive());
        return refrigerator.isActive();
    }

    public boolean toggleRefrigeratorOpen() {
        refrigerator.setOpen(!refrigerator.isOpen());
        return refrigerator.isOpen();
    }

    public boolean toggleSinkActive() {
        sink.setActive(!sink.isActive());
        return sink.isActive();
    }

}
