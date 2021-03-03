package com.company;

public class Physics {
    public double calculateForce(double mass, double acceleration) {
        return mass * acceleration;
    }

    public double calculateVelocity(double startPosition, double endPosition, double startTime, double endTime){
        return (endPosition - startPosition) / (endTime - startTime);
    }
}
