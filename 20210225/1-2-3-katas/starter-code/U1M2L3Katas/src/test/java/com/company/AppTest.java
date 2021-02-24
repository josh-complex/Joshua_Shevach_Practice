package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    App app = new App();

    @Test
    public void shouldCreateTVObjectAndReturnExpectedValuesWhenRun() {

        TV tv = app.createTV();

        String expect = "Expected method to return a TV object";
        assertTrue(expect, tv instanceof TV);

        expect = "Values should be as described in spec";
        assertEquals(expect, "Zenith", tv.getManufacturer());
        assertEquals(expect, "U2121H", tv.getModel());
        assertEquals(expect, 83, tv.getScreenSize());
        assertEquals(expect, "NBC", tv.getChannel());
        assertEquals(expect, 55, tv.getVolume());
        assertFalse(expect, tv.isPowered());
    }

    @Test
    public void shouldCreateRadioObjectAndReturnExpectedValuesWhenRun() {

        Radio radio = app.createRadio();

        String expect = "Expected method to return a Radio object";
        assertTrue(expect, radio instanceof Radio);

        expect = "Values should be as described in spec";
        assertEquals(expect, "Sony", radio.getManufacturer());
        assertEquals(expect, "V32", radio.getModel());
        assertEquals(expect, 2, radio.getNumSpeakers());
        assertEquals(expect, "WUNV", radio.getStation());
        assertEquals(expect, 30, radio.getVolume());
        assertTrue(expect, radio.isPowered());
    }

    @Test
    public void shouldCreateMicrowaveObjectAndReturnExpectedValuesWhenRun() {

        Microwave microwave = app.createMicrowave();

        String expect = "Expected method to return a Microwave object";
        assertTrue(expect, microwave instanceof Microwave);

        expect = "Values should be as described in spec";
        assertEquals(expect, "Haier", microwave.getManufacturer());
        assertEquals(expect, "X1200w", microwave.getModel());
        assertEquals(expect, 45, microwave.getSecondsLeft());
        assertEquals(expect, "12:00", microwave.getTime());
        assertFalse(expect, microwave.isRunning());
    }

    @Test
    public void shouldCreateCoffeeMakerObjectAndReturnExpectedValuesWhenRun() {

        CoffeeMaker coffeeMaker = app.createCoffeeMaker();

        String expect = "Expected method to return a CoffeeMaker object";
        assertTrue(expect, coffeeMaker instanceof CoffeeMaker);

        expect = "Values should be as described in spec";
        assertEquals(expect, "Sunbeam", coffeeMaker.getManufacturer());
        assertEquals(expect, "C12", coffeeMaker.getModel());
        assertEquals(expect, 12, coffeeMaker.getCarafeSize());
        assertEquals(expect, 8, coffeeMaker.getCupsLeft());
        assertTrue(expect, coffeeMaker.isPowered());
    }

    @Test
    public void shouldCreateCarObjectAndReturnExpectedValuesWhenRun() {

        Car car = app.createCar();

        String expect = "Expected method to return a Car object";
        assertTrue(expect, car instanceof Car);

        expect = "Values should be as described in spec";
        assertEquals(expect, "Honda", car.getMake());
        assertEquals(expect, "Accord", car.getModel());
        assertEquals(expect, "Sedan", car.getType());
        assertEquals(expect, "Blue", car.getColor());
        assertEquals(expect, "2.6L V6", car.getEngine());
        assertEquals(expect, "CVT", car.getTransmission());
        assertEquals(expect, 4, car.getNumDoors());
        assertEquals(expect, 31.7, car.getMpg(), 0.001);
        assertEquals(expect, 25218, car.getMilesDriven());
    }

    @Test
    public void shouldCreateComputerMouseObjectAndReturnExpectedValuesWhenRun() {

        ComputerMouse computerMouse = app.createComputerMouse();

        String expect = "Expected method to return a ComputerMouse object";
        assertTrue(expect, computerMouse instanceof ComputerMouse);

        expect = "Values should be as described in spec";
        assertEquals(expect, "Razer", computerMouse.getManufacturer());
        assertEquals(expect, "Naga", computerMouse.getModel());
        assertEquals(expect, 960, computerMouse.getxPosition());
        assertEquals(expect, 540, computerMouse.getyPosition());
        assertArrayEquals(expect, new int[] {0,0}, computerMouse.getLastClickedLocation());
    }


}
