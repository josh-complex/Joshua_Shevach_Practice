package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public void printKeys(Map<String, String> map){
        for (String itr : map.keySet())
            System.out.println(itr);
    }


    public void printValues(Map<String, String> map) {
        for (String itr : map.values())
            System.out.println(itr);
    }

    public void printKeysAndValues(Map<String, String> map) {
        for (String itr : map.keySet())
            System.out.printf("%s: %s\n", itr, map.get(itr));
    }

    public Map<String, Integer> mapFun(Map<String, Integer> cars) {
        cars.put("Ford Explorer", 2012);
        cars.put("Smart Fortwo", 2013);
        cars.remove("Jeep Wrangler");
        return cars;
    }

    public Map<String, List<Car>> listCars(List<Car> carList) {
        Map<String, List<Car>> carMap = new HashMap<>();

        carMap.put("Toyota", carList.stream()
                .filter(x -> x.getMake().equals("Toyota"))
                .collect(Collectors.toList()));

        carMap.put("Ford", carList.stream()
                .filter(x -> x.getMake().equals("Ford"))
                .collect(Collectors.toList()));

        carMap.put("Honda", carList.stream()
                .filter(x -> x.getMake().equals("Honda"))
                .collect(Collectors.toList()));

        return carMap;
    }
}
