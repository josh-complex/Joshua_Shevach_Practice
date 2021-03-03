package com.company;

import java.util.Dictionary;

public class Physics {
    public double calculateForce(double mass, double acceleration) {
        return mass * acceleration;
    }

    public double calculateVelocity(double startPosition, double endPosition, double startTime, double endTime) {
        double temp = (endPosition - startPosition) / (endTime - startTime);
        return !Double.isNaN(temp) && Double.isFinite(temp) ? temp : 0;
    }

    public double calculateMass(double weight, double acceleration) {
        double temp = weight / acceleration;
        return !Double.isNaN(temp) && Double.isFinite(temp) ? temp : 0;
    }

    public double calculateAcceleration(double startVelocity, double endVelocity, double startTime, double endTime) {
        double temp = (endVelocity - startVelocity) / (endTime - startTime);
        return !Double.isNaN(temp) && Double.isFinite(temp) ? temp : 0;
    }

    public double calculateDistance(double velocity, double time) {
        return velocity * time;
    }
}
