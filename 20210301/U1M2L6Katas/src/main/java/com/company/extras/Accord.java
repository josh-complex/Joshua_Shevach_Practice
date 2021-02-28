package com.company.extras;

public class Accord extends Sedan {

    protected String year;
    protected Engine engine;
    protected Transmission transmission;


    public Accord(String year, Engine engine, Transmission transmission, int numDoors, boolean poweredWindows, String make, String model, int milesTraveled) {
        super(numDoors, poweredWindows, make, model, milesTraveled);
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
    }
}
