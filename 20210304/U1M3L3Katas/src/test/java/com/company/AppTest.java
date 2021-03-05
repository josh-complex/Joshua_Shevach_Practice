package com.company;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {

    private App tester;

    private static HashMap<String, String> test1;
    private static HashMap<String, String> test2;
    private static HashMap<String, String> test3;

    private static HashMap<String, Integer> cars1;
    private static HashMap<String, Integer> cars2;

    private static FileInputStream getOutput(String dir, int iteration) throws FileNotFoundException {
        final FileInputStream fips = new FileInputStream(new File("testfiles/" + dir + "/output" + iteration + ".txt"));
        return fips;
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        tester = new App();

        test1 = new HashMap<>();
        test2 = new HashMap<>();
        test3 = new HashMap<>();

        test1.put("apple", "barney");
        test1.put("orchard", "marshall");
        test1.put("banana", "ted");
        test1.put("cat", "lily");
        test1.put("dance", "robin");

        test2.put("Pitcher", "Mike Foltynewicz");
        test2.put("Catcher", "Brian McCann");
        test2.put("First Baseman", "Freddie Freeman");
        test2.put("Second Baseman", "Ozzie Albies");
        test2.put("Third Baseman", "Josh Donaldson");
        test2.put("Shortstop", "Dansby Swanson");
        test2.put("Left Fielder", "Ronald Acuna, Jr.");
        test2.put("Center Fielder", "Ender Inciarte");
        test2.put("Right Fielder", "Nick Markakis");

        test3.put("Instructor", "Kesh");
        test3.put("Assistant Instructor", "Deep");
        test3.put("SSM", "Nate");

        cars1 = new HashMap<>();

        cars1.put("Toyota Camry", 2012);
        cars1.put("Chevy Camaro", 1969);
        cars1.put("Hyundai Genesis", 2015);
        cars1.put("Jeep Wrangler", 2003);
        cars1.put("Honda Civic", 2018);
        cars1.put("Pontiac GTO", 1964);

        cars2 = new HashMap<>();

        cars2.put("Toyota Camry", 2012);
        cars2.put("Chevy Camaro", 1969);
        cars2.put("Hyundai Genesis", 2015);
        cars2.put("Jeep Wrangler", 2003);
        cars2.put("Honda Civic", 2018);
        cars2.put("Pontiac GTO", 1964);
        cars2.put("Chevy Silverado", 2013);
        cars2.put("Hyundai Sonata", 2019);
        cars2.put("Nissan Altima", 1964);


    }

     @Test
     public void shouldPrintKeys() throws Exception {
         Scanner scan = new Scanner(getOutput("keys", 1));

         tester.printKeys(test1);
         String output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all keys";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();

         scan = new Scanner(getOutput("keys", 2));

         tester.printKeys(test2);
         output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all keys";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();

         scan = new Scanner(getOutput("keys", 3));

         tester.printKeys(test3);
         output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all keys";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();
     }

     @Test
     public void shouldPrintValues() throws Exception {
         Scanner scan = new Scanner(getOutput("values", 1));

         tester.printValues(test1);
         String output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all values";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();

         scan = new Scanner(getOutput("values", 2));

         tester.printValues(test2);
         output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all values";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();

         scan = new Scanner(getOutput("values", 3));

         tester.printValues(test3);
         output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all values";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();
     }

     @Test
     public void shouldPrintKeysAndValues() throws Exception {
         Scanner scan = new Scanner(getOutput("keysAndValues", 1));

         tester.printKeysAndValues(test1);
         String output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all keys and values";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();

         scan = new Scanner(getOutput("keysAndValues", 2));

         tester.printKeysAndValues(test2);
         output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all keys and values";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();

         scan = new Scanner(getOutput("keysAndValues", 3));

         tester.printKeysAndValues(test3);
         output = systemOutRule.getLog();

         while(scan.hasNext()) {
             String failMessage = "Expected to output all keys and values";
             assertTrue(failMessage, output.contains(scan.nextLine()));
         }

         systemOutRule.clearLog();
     }

     @Test
     public void shouldModifyAndReturnMap() {
         HashMap<String, Integer> expected1 = new HashMap<>();

         expected1.put("Toyota Camry", 2012);
         expected1.put("Chevy Camaro", 1969);
         expected1.put("Hyundai Genesis", 2015);
         expected1.put("Honda Civic", 2018);
         expected1.put("Pontiac GTO", 1964);
         expected1.put("Ford Explorer", 2012);
         expected1.put("Smart Fortwo", 2013);

         HashMap<String, Integer> expected2 = new HashMap<>();

         expected2.put("Toyota Camry", 2012);
         expected2.put("Chevy Camaro", 1969);
         expected2.put("Hyundai Genesis", 2015);
         expected2.put("Honda Civic", 2018);
         expected2.put("Pontiac GTO", 1964);
         expected2.put("Chevy Silverado", 2013);
         expected2.put("Hyundai Sonata", 2019);
         expected2.put("Nissan Altima", 1964);
         expected2.put("Ford Explorer", 2012);
         expected2.put("Smart Fortwo", 2013);

         Map<String, Integer> output1 = tester.mapFun(cars1);
         Map<String, Integer> output2 = tester.mapFun(cars2);

         String failMessage = "Expected Jeep to be removed and Explorer and Fortwo to be added to the Map";
         assertEquals(failMessage, output1, expected1);
         assertEquals(failMessage, output2, expected2);
     }

     @Test
     public void shouldReturnCarListMap() {
         ArrayList<Car> carList = new ArrayList<>();
         ArrayList<Car> carList2 = new ArrayList<>();

         Car camry = new Car("Toyota", "Camry");
         Car corolla = new Car("Toyota", "Corolla");
         Car highlander = new Car("Toyota", "Highlander");
         Car mustang = new Car("Ford", "Mustang");
         Car fusion = new Car("Ford", "Fusion");
         Car taurus = new Car("Ford", "Taurus");
         Car accord = new Car("Honda", "Accord");
         Car civic = new Car("Honda", "Civic");
         Car fit = new Car("Honda", "Fit");

         carList.add(camry);
         carList.add(corolla);
         carList.add(highlander);
         carList.add(mustang);
         carList.add(fusion);
         carList.add(taurus);
         carList.add(accord);
         carList.add(civic);
         carList.add(fit);

         carList2.add(camry);
         carList2.add(mustang);
         carList2.add(accord);

         ArrayList<Car> toyotaList = new ArrayList<>();
         ArrayList<Car> toyotaList2 = new ArrayList<>();

         toyotaList.add(camry);
         toyotaList2.add(camry);
         toyotaList.add(corolla);
         toyotaList.add(highlander);

         ArrayList<Car> fordList = new ArrayList<>();
         ArrayList<Car> fordList2 = new ArrayList<>();

         fordList.add(mustang);
         fordList2.add(mustang);
         fordList.add(fusion);
         fordList.add(taurus);

         ArrayList<Car> hondaList = new ArrayList<>();
         ArrayList<Car> hondaList2 = new ArrayList<>();

         hondaList.add(accord);
         hondaList2.add(accord);
         hondaList.add(civic);
         hondaList.add(fit);

         Map<String, ArrayList<Car>> expected = new HashMap<>();

         expected.put("Toyota", toyotaList);
         expected.put("Ford", fordList);
         expected.put("Honda", hondaList);

         Map<String, ArrayList<Car>> expected2 = new HashMap<>();

         expected2.put("Toyota", toyotaList2);
         expected2.put("Ford", fordList2);
         expected2.put("Honda", hondaList2);

         Map<String, List<Car>> output = tester.listCars(carList);
         Map<String, List<Car>> output2 = tester.listCars(carList2);

         String failMessage = "Expected a map with String keys and a List of Car values";
         assertEquals(failMessage, output, expected);
         assertEquals(failMessage, output2, expected2);

     }


}
