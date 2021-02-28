package com.company;

import com.company.utilities.Thermostat;

public class Home {

    private Bedroom masterBedroom;
    private Bedroom guestBedroom;

    private Bathroom masterBathroom;
    private Bathroom guestBathroom;

    private Kitchen kitchen;
    private Patio patio;
    private Basement basement;
    private Garage garage;

    private final Thermostat thermostat = new Thermostat();

    public Home() {
    }

    public Home(Bedroom masterBedroom, Bedroom guestBedroom, Bathroom masterBathroom, Bathroom guestBathroom, Kitchen kitchen, Patio patio, Basement basement, Garage garage) {
        this.masterBedroom = masterBedroom;
        this.guestBedroom = guestBedroom;
        this.masterBathroom = masterBathroom;
        this.guestBathroom = guestBathroom;
        this.kitchen = kitchen;
        this.patio = patio;
        this.basement = basement;
        this.garage = garage;
    }

    public Bedroom getMasterBedroom() {
        return masterBedroom;
    }

    public void setMasterBedroom(Bedroom masterBedroom) {
        this.masterBedroom = masterBedroom;
    }

    public Bedroom getGuestBedroom() {
        return guestBedroom;
    }

    public void setGuestBedroom(Bedroom guestBedroom) {
        this.guestBedroom = guestBedroom;
    }

    public Bathroom getMasterBathroom() {
        return masterBathroom;
    }

    public void setMasterBathroom(Bathroom masterBathroom) {
        this.masterBathroom = masterBathroom;
    }

    public Bathroom getGuestBathroom() {
        return guestBathroom;
    }

    public void setGuestBathroom(Bathroom guestBathroom) {
        this.guestBathroom = guestBathroom;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Basement getBasement() {
        return basement;
    }

    public void setBasement(Basement basement) {
        this.basement = basement;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
