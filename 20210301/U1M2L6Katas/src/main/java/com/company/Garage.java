package com.company;

import com.company.abstracts.Room;
import com.company.enums.FlooringType;
import com.company.extras.interfaces.Vehicle;

public class Garage extends Room {

    private boolean isOpen;
    private int numSpaces;
    private Vehicle[] vehicles;

    public Garage() {
    }

    public Garage(int numSpaces, double roomLength, double roomWidth, double ceilingHeight, FlooringType flooringType, int numLights) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.ceilingHeight = ceilingHeight;
        this.flooringType = flooringType;
        this.numSpaces = numSpaces;
        vehicles = new Vehicle[numSpaces];
        this.setLights(numLights);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getNumSpaces() {
        return numSpaces;
    }

    public void setNumSpaces(int numSpaces) {
        this.numSpaces = numSpaces;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle[] vehicles){
        this.vehicles = vehicles;
    }

    public boolean tryParkVehicle(Vehicle vehicle){
        for(Vehicle itr : vehicles){
            if(itr == null) {
                itr = vehicle;
                System.out.println("Car parked in garage");
                return true;
            }
        }
        System.out.println("Can't park your car here");
        return false;
    }

    public void removeVehicle(Vehicle vehicle){
        for(Vehicle itr : vehicles){
            if(itr == vehicle){
                itr = null;
                System.out.println("Car removed from garage");
                return;
            }
        }
        System.out.println("Car not in garage!");
    }
}
