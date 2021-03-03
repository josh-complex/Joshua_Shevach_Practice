package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicsTest {

    Physics physics;

    @Before
    public void beforeTests(){
        physics = new Physics();
    }

    @Test
    public void shouldCalculateForceWhenGivenMassAndAcceleration(){
        // Positive case
        assertEquals(25, physics.calculateForce(5, 5), 0.0001);
        // Single negative case
        assertEquals(-21.7, physics.calculateForce(7, -3.1), 0.0001);
        assertEquals(-10, physics.calculateForce(-2.5, 4), 0.0001);
        // Double negative case
        assertEquals(100, physics.calculateForce(-5, -20), 0.0001);
        // Zero case
        assertEquals(0, physics.calculateForce(0, 52.9), 0.0001);
        assertEquals(0, physics.calculateForce(33.33, 0), 0.0001);
    }

    @Test
    public void shouldCalculateVelocityGivenPositionsOverTime(){
        // From zero position forward case
        double startPosition = 0;
        double endPosition = 23;
        double startTime = 0;
        double endTime = 4;
        assertEquals(5.75, physics.calculateVelocity(startPosition, endPosition, startTime, endTime), 0.0001);
        // From zero position backward case
        endPosition = -52.37;
        endTime = 3.72;
        assertEquals(-14.0779, physics.calculateVelocity(startPosition, endPosition, startTime, endTime), 0.0001);
        // Zero case
        startPosition = 0;
        endPosition = 0;
        assertEquals(0, physics.calculateVelocity(startPosition, endPosition, startTime, endTime), 0.0001);
        startTime = 0;
        endTime = 0;
        assertEquals(0, physics.calculateVelocity(startPosition, endPosition, startTime, endTime), 0.0001);
    }

    @Test
    public void shouldCalculateMassWhenGivenWeightOverSinglePointAcceleration(){
        // Average adult male rest mass case
        assertEquals(-20.17329255, physics.calculateMass(197.9, -9.81), 0.0001);
        // Modified acceleration case
        assertEquals(-9.989904088, physics.calculateMass(197.9, -19.81), 0.0001);
        assertEquals(9.989904088, physics.calculateMass(197.9, 19.81), 0.0001);
        // Zero case
        assertEquals(0, physics.calculateMass(0, -9.81), 0.0001);
        assertEquals(0, physics.calculateMass(197.9, 0), 0.0001);
    }

    @Test
    public void shouldCalculateForceWhenGivenAccelerationAndMassUsingTheCalculateMassMethod(){
        assertEquals(24.8, physics.calculateForce(physics.calculateMass(10, 2), 4.96), 0.0002);
        assertEquals(0, physics.calculateForce(physics.calculateMass(5, 5), 0), 0.0001);
        assertEquals(0, physics.calculateForce(physics.calculateMass(5, 0), 5), 0.0001);
        assertEquals(0, physics.calculateForce(physics.calculateMass(0, 5), 5), 0.0001);
    }

    @Test
    public void shouldCalculateAccelerationWhenGivenStartAndEndVelocityOverTime(){
        // From positive position forward case
        double startVelocity = 12.82;
        double endVelocity = 76.911;
        double startTime = 0;
        double endTime = 5;
        assertEquals(12.8182, physics.calculateAcceleration(startVelocity, endVelocity, startTime, endTime), 0.0001);
        // From positive position backward case
        endVelocity = -52.37;
        endTime = 3.72;
        assertEquals(-17.5241935, physics.calculateAcceleration(startVelocity, endVelocity, startTime, endTime), 0.0001);
        // Zero case
        startVelocity = 0;
        endVelocity = 0;
        assertEquals(0, physics.calculateAcceleration(startVelocity, endVelocity, startTime, endTime), 0.0001);

        startTime = 0;
        endTime = 0;
        assertEquals(0, physics.calculateAcceleration(startVelocity, endVelocity, startTime, endTime), 0.0001);
    }

    @Test
    public void shouldCalculateAccelerationGivenTimeAndVelocityUsingCalculateVelocityMethod(){
        // Using calculateVelocity case
        double startTime = 0;
        double endTime = 5;
        double startVelocity = physics.calculateVelocity(0, 20, 0, 5);
        double endVelocity = physics.calculateVelocity(20, 50, 3, 7);
        double temp = (endVelocity - startVelocity) / (endTime - startTime);

        double expectation = !Double.isNaN(temp) && Double.isFinite(temp)? temp : 0;
        assertEquals(expectation, physics.calculateAcceleration(startVelocity, endVelocity, startTime, endTime), 0.0001);

        startTime = 0;
        endTime = 0;
        assertEquals(0, physics.calculateAcceleration(startVelocity, endVelocity, startTime, endTime), 0.0001);
    }

    @Test
    public void shouldCalculateDistanceGivenVelocityByTime(){
        // Positive case
        double velocity = 7.4;
        double time = 5;
        assertEquals(37, physics.calculateDistance(velocity, time), 0.0001);
        // Negative case
        velocity *= -1;
        System.out.println(velocity);
        System.out.println(time);
        assertEquals(-37, physics.calculateDistance(velocity, time), 0.0001);
        // Zero case
        assertEquals(0, physics.calculateDistance(0, time), 0.0001);

        // Using calculateVelocity case
        double calculatedVelocity = physics.calculateVelocity(-25, 38.91, 1.054, 8.9);
        assertEquals(calculatedVelocity * time, physics.calculateDistance(calculatedVelocity, time), 0.0001);
    }

}
