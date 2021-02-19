package com.company;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void shouldReturnDifferenceBetweenTwoInputsWhenTwoInputsAreGiven() {
        String failMessage = "Expected method to return difference of arguments";
        assertEquals(failMessage, 3, App.subtract(7, 4));
        assertEquals(failMessage, -15, App.subtract(2, 17));
        assertEquals(failMessage, 30, App.subtract(60, 30));
    }

    @Test
    public void shouldReturnDifferenceBetweenTwoInputsOrZeroIfDifferenceIsNegativeWhenTwoInputsAreGiven() {
        String failMessage = "Expected method to return difference of arguments, or zero if the difference is negative";
        assertEquals(failMessage, 3, App.subtractOrZero(7, 4));
        assertEquals(failMessage, 0, App.subtractOrZero(2, 17));
        assertEquals(failMessage, 6, App.subtractOrZero(25, 19));
        assertEquals(failMessage, 0, App.subtractOrZero(1, 2));
    }

    @Test
    public void shouldReturnLargestOfThreeArgumentsWhenThreeArgumentsAreGiven() {
        String failMessage = "Expected method to return largest of three arguments";
        assertEquals(failMessage, 7, App.max(7, 4, 5));
        assertEquals(failMessage, 104, App.max(2, 17, 104));
        assertEquals(failMessage, 25, App.max(25, 19, -3));
    }

    @Test
    public void shouldReturnSmallestOfThreeArgumentsWhenThreeArgumentsAreGiven() {
        String failMessage = "Expected method to return smallest of three arguments";
        assertEquals(failMessage, 4, App.min(7, 4, 5));
        assertEquals(failMessage, 2, App.min(2, 17, 104));
        assertEquals(failMessage, -3, App.min(25, 19, -3));
    }

    @Test
    public void shouldReturnBooleanIfFirstArgumentIsLargerThanSecondWhenTwoArgumentsAreGiven() {
        String failMessage = "Expected method to return a boolean if the first argument is larger than the second";
        assertTrue(failMessage, App.isLarger(4, 3));
        assertTrue(failMessage, !App.isLarger(2, 30));
        assertTrue(failMessage, App.isLarger(-5, -10));
    }

}
