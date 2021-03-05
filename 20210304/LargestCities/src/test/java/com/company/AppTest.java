package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AppTest {

    Map<String, City> cityMap = new HashMap<>();
    Map<String, City> expectedMap1 = new HashMap<>();
    Map<String, City> expectedMap2 = new HashMap<>();
    @Before
    public void beforeTests(){
        cityMap.put("New York", new City("New York", 8654321));
        cityMap.put("California", new City("Los Angeles", 4563218));
        cityMap.put("Illinois", new City("Chicago", 2716520));
        cityMap.put("Colorado", new City("Denver", 704621));
        cityMap.put("Iowa", new City("Des Moines", 217521));
        cityMap.put("Georgia", new City("Atlanta", 486213));

        expectedMap1.put("New York", new City("New York", 8654321));
        expectedMap1.put("California", new City("Los Angeles", 4563218));
        expectedMap1.put("Illinois", new City("Chicago", 2716520));

        expectedMap2.put("New York", new City("New York", 8654321));
        expectedMap2.put("California", new City("Los Angeles", 4563218));
        expectedMap2.put("Illinois", new City("Chicago", 2716520));
        expectedMap2.put("Colorado", new City("Denver", 704621));
        expectedMap2.put("Georgia", new City("Atlanta", 486213));
    }
    @Test
    public void shouldFilterByPopulationWhenGivenMapOfStatesWithCities(){

        assertEquals(expectedMap1, App.filterByPopulation(cityMap, 1000000));
        assertEquals(expectedMap2, App.filterByPopulation(cityMap, 400000));

    }

}
