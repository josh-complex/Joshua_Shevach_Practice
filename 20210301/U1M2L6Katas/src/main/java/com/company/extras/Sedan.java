package com.company.extras;

public class Sedan extends Car {

    protected int numDoors;
    protected boolean poweredWindows;

    public Sedan(int numDoors, boolean poweredWindows, String make, String model, int milesTraveled) {
        super(make, model, milesTraveled);
        this.numDoors = numDoors;
        this.poweredWindows = poweredWindows;
    }
}
