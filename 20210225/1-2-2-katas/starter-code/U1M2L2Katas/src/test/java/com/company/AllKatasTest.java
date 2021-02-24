package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class AllKatasTest {

    @Test
    public void shouldInstantiateCarAndGetExpectedValuesWhenRun() {

        Car car = new Car("Honda", "Accord", "Sedan", "Blue", "2.6L V6", "CVT", 4, 31.7, 25218);

        String expect = "Expected to be able to instantiate a Car object";
        assertTrue(expect, car instanceof Car);

        expect = "Values should be initialized by the constructor and accessible by getters";
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
    public void shouldInstantiateCoffeeMakerAndGetExpectedValuesWhenRun() {

        CoffeeMaker coffeeMaker = new CoffeeMaker("Sunbeam", "C12", 12, 8, true);

        String expect = "Expected to be able to instantiate a CoffeeMaker object";
        assertTrue(expect, coffeeMaker instanceof CoffeeMaker);

        expect = "Values should be initialized by the constructor and accessible by getters";
        assertEquals(expect, "Sunbeam", coffeeMaker.getManufacturer());
        assertEquals(expect, "C12", coffeeMaker.getModel());
        assertEquals(expect, 12, coffeeMaker.getCarafeSize());
        assertEquals(expect, 8, coffeeMaker.getCupsLeft());
        assertTrue(expect, coffeeMaker.isPowered());
    }

    @Test
    public void shouldInstantiateComputerMouseAndGetExpectedValuesWhenRun() {

        ComputerMouse computerMouse = new ComputerMouse("Razer", "Naga", 960, 540, new int[] {0, 0});

        String expect = "Expected to be able to instantiate a ComputerMouse object";
        assertTrue(expect, computerMouse instanceof ComputerMouse);

        expect = "Values should be initialized by the constructor and accessible by getters";
        assertEquals(expect, "Razer", computerMouse.getManufacturer());
        assertEquals(expect, "Naga", computerMouse.getModel());
        assertEquals(expect, 960, computerMouse.getxPosition());
        assertEquals(expect, 540, computerMouse.getyPosition());
        assertArrayEquals(expect, new int[] {0,0}, computerMouse.getLastClickedLocation());
    }

    @Test
    public void shouldInstantiateMicrowaveAndGetExpectedValuesWhenRun() {

        Microwave microwave = new Microwave("Haier", "X1200w", 45, "12:00", false);

        String expect = "Expected to be able to instantiate a Microwave object";
        assertTrue(expect, microwave instanceof Microwave);

        expect = "Values should be initialized by the constructor and accessible by getters";
        assertEquals(expect, "Haier", microwave.getManufacturer());
        assertEquals(expect, "X1200w", microwave.getModel());
        assertEquals(expect, 45, microwave.getSecondsLeft());
        assertEquals(expect, "12:00", microwave.getTime());
        assertFalse(expect, microwave.isRunning());
    }

    @Test
    public void shouldInstantiateRadioAndGetExpectedValuesWhenRun() {

        Radio radio = new Radio("Sony", "V32", 2, "WUNV", 30,true);

        String expect = "Expected to be able to instantiate a Radio object";
        assertTrue(expect, radio instanceof Radio);

        expect = "Values should be initialized by the constructor and accessible by getters";
        assertEquals(expect, "Sony", radio.getManufacturer());
        assertEquals(expect, "V32", radio.getModel());
        assertEquals(expect, 2, radio.getNumSpeakers());
        assertEquals(expect, "WUNV", radio.getStation());
        assertEquals(expect, 30, radio.getVolume());
        assertTrue(expect, radio.isPowered());
    }

    @Test
    public void shouldInstantiateTVAndGetExpectedValuesWhenRun() {

        TV tv = new TV("Zenith", "U2121H", 83, "NBC", 55,false);

        String expect = "Expected to be able to instantiate a TV object";
        assertTrue(expect, tv instanceof TV);

        expect = "Values should be initialized by the constructor and accessible by getters";
        assertEquals(expect, "Zenith", tv.getManufacturer());
        assertEquals(expect, "U2121H", tv.getModel());
        assertEquals(expect, 83, tv.getScreenSize());
        assertEquals(expect, "NBC", tv.getChannel());
        assertEquals(expect, 55, tv.getVolume());
        assertFalse(expect, tv.isPowered());
    }


}
