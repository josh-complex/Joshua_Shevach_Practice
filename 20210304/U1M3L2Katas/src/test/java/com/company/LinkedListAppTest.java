// package com.company;

// import org.junit.Before;
// import org.junit.Test;

// import java.util.LinkedList;
// import java.util.Arrays;

// import static org.junit.Assert.*;

// public class LinkedListAppTest {

//     private static int[] intArray1 = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
//     private static int[] intArray2 = new int[] {-3, -17};
//     private static int[] intArray3 = new int[] {55, 2323, 102, 7};
//     private static int[] intArray4 = new int[] {2, 4, 5, 3,
//             7, 6, 1, 9,
//             10, 13, 56, 43,
//             17, 89, 3, 24,
//             37, 12, 101, 112};

//     private static LinkedList<Integer> intTest1;
//     private static LinkedList<Integer> intTest2;
//     private static LinkedList<Integer> intTest3;
//     private static LinkedList<Integer> intTest4;

//     private static LinkedList<String> stringTest1;
//     private static LinkedList<String> stringTest2;
//     private static LinkedList<String> stringTest3;

//     @Before
//     public void setup() {

//         intTest1 = new LinkedList<>();
//         for(int num : intArray1) {
//             intTest1.add(num);
//         }
//         intTest2 = new LinkedList<>();
//         for(int num : intArray2) {
//             intTest2.add(num);
//         }
//         intTest3 = new LinkedList<>();
//         for(int num : intArray3) {
//             intTest3.add(num);
//         }
//         intTest4 = new LinkedList<>();
//         for(int num : intArray4) {
//             intTest4.add(num);
//         }

//         stringTest1 = new LinkedList<>(Arrays.asList("foo", "bar", "baz", "qux"));
//         stringTest2 = new LinkedList<>(Arrays.asList("Java", "C#", "C++", "Python", "C", "JavaScript", "Apple"));
//         stringTest3 = new LinkedList<>(Arrays.asList("Dell", "Lenovo"));
//     }

//     @Test
//    public void shouldReturnSumOfInts() {
//        String failMessage = "Expected method to return sum of integers";
//        assertEquals(failMessage, 28, LinkedListApp.total(intTest1));
//        assertEquals(failMessage, -20, LinkedListApp.total(intTest2));
//        assertEquals(failMessage, 2487, LinkedListApp.total(intTest3));
//        assertEquals(failMessage, 554, LinkedListApp.total(intTest4));
//    }
//
//    @Test
//    public void shouldReturnSumOfIntsAtEvenIndices() {
//        String failMessage = "Expected method to return sum of only even indices";
//        assertEquals(failMessage, 12, LinkedListApp.totalEven(intTest1));
//        assertEquals(failMessage, -3, LinkedListApp.totalEven(intTest2));
//        assertEquals(failMessage, 157, LinkedListApp.totalEven(intTest3));
//        assertEquals(failMessage, 239, LinkedListApp.totalEven(intTest4));
//    }
//
//    @Test
//    public void shouldSwapFirstAndLastIndices() {
//        LinkedList<String> output1 = new LinkedList<>(Arrays.asList("qux", "bar", "baz", "foo"));
//        LinkedList<String> output2 = new LinkedList<>(Arrays.asList("Apple", "C#", "C++", "Python", "C", "JavaScript", "Java"));
//        LinkedList<String> output3 = new LinkedList<>(Arrays.asList("Lenovo", "Dell"));
//
//        String failMessage = "Expected method to swap first and last indices";
//        assertEquals(failMessage, output1, LinkedListApp.swapFirstAndLast(stringTest1));
//        assertEquals(failMessage, output2, LinkedListApp.swapFirstAndLast(stringTest2));
//        assertEquals(failMessage, output3, LinkedListApp.swapFirstAndLast(stringTest3));
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
//        LinkedList<Integer> output1 = new LinkedList<>();
//        for(int num : out1) {
//            output1.add(num);
//        }
//        LinkedList<Integer> output2  = new LinkedList<>();
//        for(int num : out2) {
//            output2.add(num);
//        }
//        LinkedList<Integer> output3  = new LinkedList<>();
//        for(int num : out3) {
//            output3.add(num);
//        }
//        LinkedList<Integer> output4  = new LinkedList<>();
//        for(int num : out4) {
//            output4.add(num);
//        }
//
//        String failMessage = "Expected method to reverse an array of ints";
//        assertEquals(failMessage, output1, LinkedListApp.reverse(intTest1));
//        assertEquals(failMessage, output2, LinkedListApp.reverse(intTest2));
//        assertEquals(failMessage, output3, LinkedListApp.reverse(intTest3));
//        assertEquals(failMessage, output4, LinkedListApp.reverse(intTest4));
//    }
//
//    @Test
//    public void shouldReturnNewListOfValuesLessThanFive() {
//        int[] out1 = new int[] {0, 1, 2, 3, 4};
//        int[] out2 = new int[] {-3, -17};
//        int[] out4 = new int[] {2, 4, 3, 1, 3};
//
//        LinkedList<Integer> output1 = new LinkedList<>();
//        for(int num : out1) {
//            output1.add(num);
//        }
//        LinkedList<Integer> output2  = new LinkedList<>();
//        for(int num : out2) {
//            output2.add(num);
//        }
//        LinkedList<Integer> output4  = new LinkedList<>();
//        for(int num : out4) {
//            output4.add(num);
//        }
//
//        String failMessage = "Expected method to return an array with values less than 5";
//        assertEquals(failMessage, output1, LinkedListApp.lessThanFive(intTest1));
//        assertEquals(failMessage, output2, LinkedListApp.lessThanFive(intTest2));
//        assertNull(failMessage, LinkedListApp.lessThanFive(intTest3));
//        assertEquals(failMessage, output4, LinkedListApp.lessThanFive(intTest4));
//    }
//
//    @Test
//    public void shouldReturnListWithValuesGreaterThanFiveAndListWithValuesLessThanFive() {
//        int[][] out1 = new int[][] { {0, 1, 2, 3, 4}, {5, 6, 7} };
//        int[][] out2 = new int[][] { {-3, -17}, {} };
//        int[][] out3 = new int[][] { {}, {55, 2323, 102, 7} };
//        int[][] out4 = new int[][] { {2, 4, 3, 1, 3}, {5, 7, 6, 9, 10, 13, 56, 43, 17, 89, 24, 37, 12, 101, 112} };
//
//        LinkedList<LinkedList<Integer>> output1 = new LinkedList<>();
//        for(int[] arr : out1) {
//            LinkedList<Integer> temp = new LinkedList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output1.add(temp);
//        }
//        LinkedList<LinkedList<Integer>> output2  = new LinkedList<>();
//        for(int[] arr : out2) {
//            LinkedList<Integer> temp = new LinkedList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output2.add(temp);
//        }
//        LinkedList<LinkedList<Integer>> output3  = new LinkedList<>();
//        for(int[] arr : out3) {
//            LinkedList<Integer> temp = new LinkedList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output3.add(temp);
//        }
//        LinkedList<LinkedList<Integer>> output4  = new LinkedList<>();
//        for(int[] arr : out4) {
//            LinkedList<Integer> temp = new LinkedList<>();
//            for(int num : arr) {
//                temp.add(num);
//            }
//            output4.add(temp);
//        }
//
//        String failMessage = "Expected method to return an array with values less than 5";
//        assertEquals(failMessage, output1, LinkedListApp.splitAtFive(intTest1));
//        assertEquals(failMessage, output2, LinkedListApp.splitAtFive(intTest2));
//        assertEquals(failMessage, output3, LinkedListApp.splitAtFive(intTest3));
//        assertEquals(failMessage, output4, LinkedListApp.splitAtFive(intTest4));
//    }
//
//    @Test
//    public void shouldReturnListOfElementsAtOddIndicesAndListOfElementsAtEvenIndices() {
//        String[][] out1 = new String[][] { {"foo", "baz"}, {"bar", "qux"} };
//        String[][] out2 = new String[][] { {"Java", "C++", "C", "Apple"}, {"C#", "Python", "JavaScript"} };
//        String[][] out3 = new String[][] { {"Dell"}, {"Lenovo"} };
//
//        LinkedList<LinkedList<String>> output1 = new LinkedList<>();
//        for(String[] arr : out1) {
//            LinkedList<String> temp = new LinkedList<>();
//            for(String num : arr) {
//                temp.add(num);
//            }
//            output1.add(temp);
//        }
//        LinkedList<LinkedList<String>> output2  = new LinkedList<>();
//        for(String[] arr : out2) {
//            LinkedList<String> temp = new LinkedList<>();
//            for(String num : arr) {
//                temp.add(num);
//            }
//            output2.add(temp);
//        }
//        LinkedList<LinkedList<String>> output3  = new LinkedList<>();
//        for(String[] arr : out3) {
//            LinkedList<String> temp = new LinkedList<>();
//            for(String num : arr) {
//                temp.add(num);
//            }
//            output3.add(temp);
//        }
//
//        String failMessage = "Expected method to split array by even and odd index";
//        assertEquals(failMessage, output1, LinkedListApp.evensAndOdds(stringTest1));
//        assertEquals(failMessage, output2, LinkedListApp.evensAndOdds(stringTest2));
//        assertEquals(failMessage, output3, LinkedListApp.evensAndOdds(stringTest3));
//    }
// }
