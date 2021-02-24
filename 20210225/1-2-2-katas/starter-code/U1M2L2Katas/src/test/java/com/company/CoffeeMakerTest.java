package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoffeeMakerTest {

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

}
