package com.company;

public class Sedan extends Car {

    protected int numDoors;
    protected boolean poweredWindows;

    public Sedan(int numDoors, boolean poweredWindows, String make, String model, int milesTraveled) {
        super(make, model, milesTraveled);
        this.numDoors = numDoors;
        this.poweredWindows = poweredWindows;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public boolean isPoweredWindows() {
        return poweredWindows;
    }

    public void setPoweredWindows(boolean poweredWindows) {
        this.poweredWindows = poweredWindows;
    }
}
