package com.company.utilities;

import com.company.utilities.abstracts.TemperatureControlledUtility;

public class Refrigerator extends TemperatureControlledUtility {

    private String make;
    private String model;
    private float capacity;
    private float fillAmount;
    private boolean open;

    public Refrigerator(String make, String model, float capacity) {
        this.make = make;
        this.model = model;
        this.capacity = capacity;
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        System.out.println("Fridge is now running: " + active);
    }

    @Override
    public void setTemperature(float temperature){
        super.setTemperature(temperature);
        System.out.println("The fridge temperature is now: " + temperature);
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

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public float getFillAmount() {
        return fillAmount;
    }

    public void setFillAmount(float fillAmount) {
        this.fillAmount = fillAmount;
    }

    public boolean isFull() {
        return fillAmount / capacity >= 1;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
