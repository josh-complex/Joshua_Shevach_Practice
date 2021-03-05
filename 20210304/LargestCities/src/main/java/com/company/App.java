package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        Map<String, City> cityMap = new HashMap<>();
        cityMap.put("New York", new City("New York", 8654321));
        cityMap.put("California", new City("Los Angeles", 4563218));
        cityMap.put("Illinois", new City("Chicago", 2716520));
        cityMap.put("Colorado", new City("Denver", 704621));
        cityMap.put("Iowa", new City("Des Moines", 217521));
        cityMap.put("Georgia", new City("Atlanta", 486213));

        for(String state : cityMap.keySet()){
            City city = cityMap.get(state);
            System.out.println(state + " has a city named " + city.getName() + " with a population of " + city.getPopulation());
        }

        System.out.println("\nStates with a population over 3000000 include: ");
        for(String state : filterByPopulation(cityMap, 3000000).keySet())
            System.out.println(state);

    }

    public static Map<String, City> filterByPopulation(Map<String, City> cityMap, int population) {

        return cityMap.entrySet()
                .stream()
                .filter(x -> x.getValue().getPopulation() > population)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
