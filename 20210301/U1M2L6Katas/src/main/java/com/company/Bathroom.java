package com.company;

import com.company.abstracts.Room;
import com.company.enums.FlooringType;
import com.company.utilities.*;

public class Bathroom extends Room {

    private boolean fullBathroom;
    private int numSinks;
    private Toilet toilet;
    private Sink[] sinks;
    private Shower shower;

    public Bathroom() {
    }

    public Bathroom(boolean fullBathroom, int numSinks, double roomLength, double roomWidth, double ceilingHeight, FlooringType flooringType, int numLights) {
        this.fullBathroom = fullBathroom;
        this.numSinks = numSinks;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.ceilingHeight = ceilingHeight;
        this.flooringType = flooringType;
        this.shower = fullBathroom ? new Shower() : null;
        this.setLights(numLights);

        // Non-user-set room requirements
        toilet = new Toilet();
        sinks = new Sink[numSinks];
        for (int i = 0; i < numSinks; i++)
            sinks[i] = new Sink();
    }

    public boolean isFullBathroom() {
        return fullBathroom;
    }

    public void setFullBathroom(boolean fullBathroom) {
        this.fullBathroom = fullBathroom;
    }

    public int getNumSinks() {
        return numSinks;
    }

    public void setNumSinks(int numSinks) {
        sinks = new Sink[numSinks];
        for (int i = 0; i < numSinks; i++)
            sinks[i] = new Sink();

        this.numSinks = numSinks;
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

    public Toilet getToilet() {
        return toilet;
    }

    public void setToilet(Toilet toilet) {
        this.toilet = toilet;
    }

    public Sink[] getSinks() {
        return sinks;
    }

    public void setSinks(Sink[] sinks) {
        this.sinks = sinks;
    }

    public Shower getShower() {
        return shower;
    }

    public void setShower(Shower shower) {
        this.shower = shower;
    }

    public boolean toggleToiletActive() {
        toilet.setActive(!toilet.isActive());
        return toilet.isActive();
    }

    public boolean toggleSinkActive(int index) {
        sinks[index].setActive(!sinks[index].isActive());
        return sinks[index].isActive();
    }

    public boolean toggleShowerActive() {
        if (shower != null) {
            shower.setActive(!shower.isActive());
            return shower.isActive();
        }
        System.out.println("There is no shower here");
        return false;
    }

}
