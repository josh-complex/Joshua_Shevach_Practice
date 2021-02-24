package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class TVTest {

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
