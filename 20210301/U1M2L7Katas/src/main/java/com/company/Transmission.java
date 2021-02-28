package com.company;

public class Transmission {

    private boolean cvt;
    private int numGears;
    private float fluidLevel;

    public Transmission(boolean cvt, int numGears, float fluidLevel) {
        this.cvt = cvt;
        this.numGears = numGears;
        this.fluidLevel = fluidLevel;
    }

    public boolean isCvt() {
        return cvt;
    }

    public void setCvt(boolean cvt) {
        this.cvt = cvt;
    }

    public int getNumGears() {
        return numGears;
    }

    public void setNumGears(int numGears) {
        this.numGears = numGears;
    }

    public float getFluidLevel() {
        return fluidLevel;
    }

    public void setFluidLevel(float fluidLevel) {
        this.fluidLevel = fluidLevel;
    }
}
