package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarTest {

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
}
