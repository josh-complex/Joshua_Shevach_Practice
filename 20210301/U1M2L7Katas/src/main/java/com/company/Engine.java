package com.company;

public class Engine {

    private int numCylinders;
    private float displacement;
    private float horsepower;
    private float torque;
    private float rpm;
    private float temperature;
    private float oilLevel;

    public Engine(int numCylinders, float displacement, float horsepower, float torque, float rpm, float temperature, float oilLevel) {
        this.numCylinders = numCylinders;
        this.displacement = displacement;
        this.horsepower = horsepower;
        this.torque = torque;
        this.rpm = rpm;
        this.temperature = temperature;
        this.oilLevel = oilLevel;
    }

    public int getNumCylinders() {
        return numCylinders;
    }

    public void setNumCylinders(int numCylinders) {
        this.numCylinders = numCylinders;
    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }

    public float getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(float horsepower) {
        this.horsepower = horsepower;
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
        this.torque = torque;
    }

    public float getRpm() {
        return rpm;
    }

    public void setRpm(float rpm) {
        this.rpm = rpm;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getOilLevel() {
        return oilLevel;
    }

    public void setOilLevel(float oilLevel) {
        this.oilLevel = oilLevel;
    }
}
