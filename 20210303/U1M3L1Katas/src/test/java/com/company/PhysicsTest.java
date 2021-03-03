package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    }

}
