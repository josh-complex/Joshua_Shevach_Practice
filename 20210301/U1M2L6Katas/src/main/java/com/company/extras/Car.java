package com.company.extras;

import com.company.extras.interfaces.Vehicle;

public class Car implements Vehicle {

    protected String make;
    protected String model;
    protected int milesTraveled;

    public Car(String make, String model, int milesTraveled) {
        this.make = make;
        this.model = model;
        this.milesTraveled = milesTraveled;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    @Override
    public void drive(int miles) {
        milesTraveled += miles;
    }

    @Override
    public void displayMilesTraveled() {

    }

}
