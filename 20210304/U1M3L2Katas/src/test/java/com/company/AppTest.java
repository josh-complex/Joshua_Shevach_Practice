// package com.company;

// import org.junit.Before;
// import org.junit.Test;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.Assert.*;

// public class AppTest {

//     private static int[] intArray1 = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
//     private static int[] intArray2 = new int[] {-3, -17};
//     private static int[] intArray3 = new int[] {55, 2323, 102, 7};
//     private static int[] intArray4 = new int[] {2, 4, 5, 3,
//             7, 6, 1, 9,
//             10, 13, 56, 43,
//             17, 89, 3, 24,
//             37, 12, 101, 112};

//     private static ArrayList<Integer> intTest1;
//     private static ArrayList<Integer> intTest2;
//     private static ArrayList<Integer> intTest3;
//     private static ArrayList<Integer> intTest4;

//     private static ArrayList<String> stringTest1;
//     private static ArrayList<String> stringTest2;
//     private static ArrayList<String> stringTest3;

//     @Before
//     public void setup() {

//         intTest1 = new ArrayList<>();
//         for(int num : intArray1) {
//             intTest1.add(num);
//         }
//         intTest2 = new ArrayList<>();
//         for(int num : intArray2) {
//             intTest2.add(num);
//         }
//         intTest3 = new ArrayList<>();
//         for(int num : intArray3) {
//             intTest3.add(num);
//         }
//         intTest4 = new ArrayList<>();
//         for(int num : intArray4) {
//             intTest4.add(num);
//         }

//         stringTest1 = new ArrayList<>(Arrays.asList("foo", "bar", "baz", "qux"));
//         stringTest2 = new ArrayList<>(Arrays.asList("Java", "C#", "C++", "Python", "C", "JavaScript", "Apple"));
//         stringTest3 = new ArrayList<>(Arrays.asList("Dell", "Lenovo"));
//     }

//    @Test
//    public void shouldReturnSumOfInts() {
//        String failMessage = "Expected method to return sum of integers";
//        assertEquals(failMessage, 28, App.total(intTest1));
//        assertEquals(failMessage, -20, App.total(intTest2));
//        assertEquals(failMessage, 2487, App.total(intTest3));
//        assertEquals(failMessage, 554, App.total(intTest4));
//    }
//
//    @Test
//    public void shouldReturnSumOfIntsAtEvenIndices() {
//        String failMessage = "Expected method to return sum of only even indices";
//        assertEquals(failMessage, 12, App.totalEven(intTest1));
//        assertEquals(failMessage, -3, App.totalEven(intTest2));
//        assertEquals(failMessage, 157, App.totalEven(intTest3));
//        assertEquals(failMessage, 239, App.totalEven(intTest4));
//    }
//
//    @Test
//    public void shouldSwapFirstAndLastIndices() {
//        ArrayList<String> output1 = new ArrayList<>(Arrays.asList("qux", "bar", "baz", "foo"));
//        ArrayList<String> output2 = new ArrayList<>(Arrays.asList("Apple", "C#", "C++", "Python", "C", "JavaScript", "Java"));
//        ArrayList<String> output3 = new ArrayList<>(Arrays.asList("Lenovo", "Dell"));
//
//        String failMessage = "Expected method to swap first and last indices";
//        assertEquals(failMessage, output1, App.swapFirstAndLast(stringTest1));
//        assertEquals(failMessage, output2, App.swapFirstAndLast(stringTest2));
//        assertEquals(failMessage, output3, App.swapFirstAndLast(stringTest3));
//    }
//
//    @Test
//    public void shouldReverseElementsInArray() {
//        int[] out1 = new int[] {7, 6, 5, 4, 3, 2, 1, 0};
//        int[] out2 = new int[] {-17, -3};
//        int[] out3 = new int[] {7, 102, 2323, 55};
//        int[] out4 = new int[] {112, 101, 12, 37,
//                24, 3, 89, 17,
//                43, 56, 13, 10,
//                9, 1, 6, 7,
//                3, 5, 4, 2};
//
//        ArrayList<Integer> output1 = new ArrayList<>();
//        for(int num : out1) {
//            output1.add(num);
//        }
//        ArrayList<Integer> output2  = new ArrayList<>();
//        for(int num : out2) {
//            output2.add(num);
//        }
//        ArrayList<Integer> output3  = new ArrayList<>();
//        for(int num : out3) {
//            output3.add(num);
//        }
//        ArrayList<Integer> output4  = new ArrayList<>();
//        for(int num : out4) {
//            output4.add(num);
//        }
//
//        String failMessage = "Expected method to reverse an array of ints";
//        assertEquals(failMessage, output1, App.reverse(intTest1));
//        assertEquals(failMessage, output2, App.reverse(intTest2));
//        assertEquals(failMessage, output3, App.reverse(intTest3));
//        assertEquals(failMessage, output4, App.reverse(intTest4));
//    }
//
//    @Test
//    public void shouldReturnNewListOfValuesLessThanFive() {
//        int[] out1 = new int[] {0, 1, 2, 3, 4};
//        int[] out2 = new int[] {-3, -17};
//        int[] out4 = new int[] {2, 4, 3, 1, 3};
//
//        ArrayList<Integer> output1 = new ArrayList<>();
//        for(int num : out1) {
//            output1.add(num);
//        }
//        ArrayList<Integer> output2  = new ArrayList<>();
//        for(int num : out2) {
//            output2.add(num);
//        }
//        ArrayList<Integer> output4  = new ArrayList<>();
//        for(int num : out4) {
//            output4.add(num);
//        }
//
//        String failMessage = "Expected method to return an array with values less than 5";
//        assertEquals(failMessage, output1, App.lessThanFive(intTest1));
//        assertEquals(failMessage, output2, App.lessThanFive(intTest2));
//        assertNull(failMessage, App.lessThanFive(intTest3));
//        assertEquals(failMessage, output4, App.lessThanFive(intTest4));
//    }
//
//    @Test
//    public void shouldReturnListWithValuesGreaterThanFiveAndListWithValuesLessThanFive() {
//        int[][] out1 = new int[][] { {0, 1, 2, 3, 4}, {5, 6, 7} };
//        int[][] out2 = new int[][] { {-3, -17}, {} };
//        int[][] out3 = new int[][] { {}, {55, 2323, 102, 7} };
//        int[][] out4 = new int[][] { {2, 4, 3, 1, 3}, {5, 7, 6, 9, 10, 13, 56, 43, 17, 89, 24, 37, 12, 101, 112} };
//
//        ArrayList<ArrayList<Integer>> output1 = new ArrayList<>();
//        for(int[] arr : out1) {
//            ArrayList<Integer> temp = new ArrayList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output1.add(temp);
//        }
//        ArrayList<ArrayList<Integer>> output2  = new ArrayList<>();
//        for(int[] arr : out2) {
//            ArrayList<Integer> temp = new ArrayList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output2.add(temp);
//        }
//        ArrayList<ArrayList<Integer>> output3  = new ArrayList<>();
//        for(int[] arr : out3) {
//            ArrayList<Integer> temp = new ArrayList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output3.add(temp);
//        }
//        ArrayList<ArrayList<Integer>> output4  = new ArrayList<>();
//        for(int[] arr : out4) {
//            ArrayList<Integer> temp = new ArrayList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output4.add(temp);
//        }
//
//        String failMessage = "Expected method to return an array with values less than 5 and array with values greater than 5";
//        assertEquals(failMessage, output1, App.splitAtFive(intTest1));
//        assertEquals(failMessage, output2, App.splitAtFive(intTest2));
//        assertEquals(failMessage, output3, App.splitAtFive(intTest3));
//        assertEquals(failMessage, output4, App.splitAtFive(intTest4));
//    }
//
//    @Test
//    public void shouldReturnListOfElementsAtOddIndicesAndListOfElementsAtEvenIndices() {
//        String[][] out1 = new String[][] { {"foo", "baz"}, {"bar", "qux"} };
//        String[][] out2 = new String[][] { {"Java", "C++", "C", "Apple"}, {"C#", "Python", "JavaScript"} };
//        String[][] out3 = new String[][] { {"Dell"}, {"Lenovo"} };
//
//        ArrayList<ArrayList<String>> output1 = new ArrayList<>();
//        for(String[] arr : out1) {
//            ArrayList<String> temp = new ArrayList<>();
//            for(String num : arr) {
//                temp.add(num);
//            }
//            output1.add(temp);
//        }
//        ArrayList<ArrayList<String>> output2  = new ArrayList<>();
//        for(String[] arr : out2) {
//            ArrayList<String> temp = new ArrayList<>();
//            for(String num : arr) {
//                temp.add(num);
//            }
//            output2.add(temp);
//        }
//        ArrayList<ArrayList<String>> output3  = new ArrayList<>();
//        for(String[] arr : out3) {
//            ArrayList<String> temp = new ArrayList<>();
//            for(String num : arr) {
//                temp.add(num);
//            }
//            output3.add(temp);
//        }
//
//        String failMessage = "Expected method to split array by even and odd index";
//        assertEquals(failMessage, output1, App.evensAndOdds(stringTest1));
//        assertEquals(failMessage, output2, App.evensAndOdds(stringTest2));
//        assertEquals(failMessage, output3, App.evensAndOdds(stringTest3));
//    }
// }
