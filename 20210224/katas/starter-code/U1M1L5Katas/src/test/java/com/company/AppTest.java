package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    private static int[] intTest1;
    private static int[] intTest2;
    private static int[] intTest3;
    private static int[] intTest4;

    private static String[] stringTest1;
    private static String[] stringTest2;
    private static String[] stringTest3;

    @Before
    public void setup() {

        intTest1 = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        intTest2 = new int[] {-3, -17};
        intTest3 = new int[] {55, 2323, 102, 7};
        intTest4 = new int[] {2, 4, 5, 3,
                7, 6, 1, 9,
                10, 13, 56, 43,
                17, 89, 3, 24,
                37, 12, 101, 112};

        stringTest1 = new String[] {"foo", "bar", "baz", "qux"};
        stringTest2 = new String[] {"Java", "C#", "C++", "Python", "C", "JavaScript", "Apple"};
        stringTest3 = new String[] {"Dell", "Lenovo"};
    }

    @Test
    public void shouldReturnSumWhenGivenArrayOfIntegers() {
        String failMessage = "Expected method to return sum of integers";
        assertEquals(failMessage, 28, App.total(intTest1));
        assertEquals(failMessage, -20, App.total(intTest2));
        assertEquals(failMessage, 2487, App.total(intTest3));
        assertEquals(failMessage, 554, App.total(intTest4));
    }

    @Test
    public void shouldReturnSumOfOddIndicesWhenGivenArrayOfIntegers() {
        String failMessage = "Expected method to return sum of only odd indices";
        assertEquals(failMessage, 16, App.totalOdd(intTest1));
        assertEquals(failMessage, -17, App.totalOdd(intTest2));
        assertEquals(failMessage, 2330, App.totalOdd(intTest3));
        assertEquals(failMessage, 315, App.totalOdd(intTest4));
    }

    @Test
    public void shouldReturnSumOfEvenIndicesWhenGivenArrayOfIntegers() {
        String failMessage = "Expected method to return sum of only even indices";
        assertEquals(failMessage, 12, App.totalEven(intTest1));
        assertEquals(failMessage, -3, App.totalEven(intTest2));
        assertEquals(failMessage, 157, App.totalEven(intTest3));
        assertEquals(failMessage, 239, App.totalEven(intTest4));
    }

    @Test
    public void shouldReturnSecondLargestValueWhenGivenArrayOfIntegers() {
        String failMessage = "Expected method to return the second largest value in an array";
        assertEquals(failMessage, 6, App.secondLargestNumber(intTest1));
        assertEquals(failMessage, -17, App.secondLargestNumber(intTest2));
        assertEquals(failMessage, 102, App.secondLargestNumber(intTest3));
        assertEquals(failMessage, 101, App.secondLargestNumber(intTest4));
    }

    @Test
    public void shouldSwapFirstAndLastIndicesWhenGivenArray() {
        String[] output1 = new String[] {"qux", "bar", "baz", "foo"};
        String[] output2 = new String[] {"Apple", "C#", "C++", "Python", "C", "JavaScript", "Java"};
        String[] output3 = new String[] {"Lenovo", "Dell"};

        String failMessage = "Expected method to swap first and last indices";
        assertArrayEquals(failMessage, output1, App.swapFirstAndLast(stringTest1));
        assertArrayEquals(failMessage, output2, App.swapFirstAndLast(stringTest2));
        assertArrayEquals(failMessage, output3, App.swapFirstAndLast(stringTest3));
    }

    @Test
    public void shouldReverseArrayWhenGivenArrayOfIntegers() {
        int[] output1 = new int[] {7, 6, 5, 4, 3, 2, 1, 0};
        int[] output2 = new int[] {-17, -3};
        int[] output3 = new int[] {7, 102, 2323, 55};
        int[] output4 = new int[] {112, 101, 12, 37,
                24, 3, 89, 17,
                43, 56, 13, 10,
                9, 1, 6, 7,
                3, 5, 4, 2};


        String failMessage = "Expected method to reverse an array of ints";
        assertArrayEquals(failMessage, output1, App.reverse(intTest1));
        assertArrayEquals(failMessage, output2, App.reverse(intTest2));
        assertArrayEquals(failMessage, output3, App.reverse(intTest3));
        assertArrayEquals(failMessage, output4, App.reverse(intTest4));
    }

    @Test
    public void shouldConcatenateArrayWhenGivenArrayOfStrings() {
        String failMessage = "Expected method to concatenate an array of strings";
        assertEquals(failMessage, "foobarbazqux", App.concatenateString(stringTest1));
        assertEquals(failMessage, "JavaC#C++PythonCJavaScriptApple", App.concatenateString(stringTest2));
        assertEquals(failMessage, "DellLenovo", App.concatenateString(stringTest3));
    }

    @Test
    public void shouldReturnNewArrayOfEveryThirdElementOfOldArrayWhenGivenArray() {
        int[] output1 = new int[] {2, 5};
        int[] output3 = new int[] {102};
        int[] output4 = new int[] {5, 6, 10, 43, 3, 12};

        String failMessage = "Expected method to return an array with every third element";
        assertArrayEquals(failMessage, output1, App.everyThird(intTest1));
        assertNull(failMessage, App.everyThird(intTest2));
        assertArrayEquals(failMessage, output3, App.everyThird(intTest3));
        assertArrayEquals(failMessage, output4, App.everyThird(intTest4));
    }

    @Test
    public void shouldReturnNewArrayOfValuesLessThanFiveWhenGivenArrayOfIntegers() {
        int[] output1 = new int[] {0, 1, 2, 3, 4};
        int[] output2 = new int[] {-3, -17};
        int[] output4 = new int[] {2, 4, 3, 1, 3};

        String failMessage = "Expected method to return an array with values less than 5";
        assertArrayEquals(failMessage, output1, App.lessThanFive(intTest1));
        assertArrayEquals(failMessage, output2, App.lessThanFive(intTest2));
        assertNull(failMessage, App.lessThanFive(intTest3));
        assertArrayEquals(failMessage, output4, App.lessThanFive(intTest4));
    }

    @Test
    public void shouldReturnArrayOfIntsLessThanFiveAndArrayOfIntsGreaterThanOrEqualToFiveWhenGivenArrayOfInts() {
        int[][] output1 = new int[][] { {0, 1, 2, 3, 4}, {5, 6, 7} };
        int[][] output2 = new int[][] { {-3, -17}, {} };
        int[][] output3 = new int[][] { {}, {55, 2323, 102, 7} };
        int[][] output4 = new int[][] { {2, 4, 3, 1, 3}, {5, 7, 6, 9, 10, 13, 56, 43, 17, 89, 24, 37, 12, 101, 112} };

        String failMessage = "Expected method to return an array with values less than 5";
        assertArrayEquals(failMessage, output1, App.splitAtFive(intTest1));
        assertArrayEquals(failMessage, output2, App.splitAtFive(intTest2));
        assertArrayEquals(failMessage, output3, App.splitAtFive(intTest3));
        assertArrayEquals(failMessage, output4, App.splitAtFive(intTest4));
    }

    @Test
    public void shouldReturnSplitArrayByEvenAndOddIndicesWhenGivenArray() {
        String[][] output1 = new String[][] { {"foo", "baz"}, {"bar", "qux"} };
        String[][] output2 = new String[][] { {"Java", "C++", "C", "Apple"}, {"C#", "Python", "JavaScript"} };
        String[][] output3 = new String[][] { {"Dell"}, {"Lenovo"} };

        String failMessage = "Expected method to split array by even and odd index";
        assertArrayEquals(failMessage, output1, App.evensAndOdds(stringTest1));
        assertArrayEquals(failMessage, output2, App.evensAndOdds(stringTest2));
        assertArrayEquals(failMessage, output3, App.evensAndOdds(stringTest3));
    }
}
