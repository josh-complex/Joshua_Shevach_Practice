package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RadioTest {

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
}
