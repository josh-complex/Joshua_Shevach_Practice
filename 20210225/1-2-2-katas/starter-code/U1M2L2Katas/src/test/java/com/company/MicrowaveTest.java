package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class MicrowaveTest {

     @Test
     public void shouldInstantiateMicrowaveAndGetExpectedValuesWhenRun() {

         Microwave microwave = new Microwave("Haier", "X1200w", 45, "12:00", false);

         String expect = "Expected to be able to instantiate a CoffeeMaker object";
         assertTrue(expect, microwave instanceof Microwave);

         expect = "Values should be initialized by the constructor and accessible by getters";
         assertEquals(expect, "Haier", microwave.getManufacturer());
         assertEquals(expect, "X1200w", microwave.getModel());
         assertEquals(expect, 45, microwave.getSecondsLeft());
         assertEquals(expect, "12:00", microwave.getTime());
         assertFalse(expect, microwave.isRunning());
     }
}
