package com.company;

import com.company.interfaces.UserIO;

public class Application {

    public static void main(String[] args) {

        UserIO userInput = new BetterInput();

        int userInt = userInput.readInt("Enter an integer");

        long userLong = userInput.readLong("Enter a long");

        double userDouble = userInput.readDouble("Enter a double");

        float userFloat = userInput.readFloat("Enter a float");

        String userString = userInput.readString("Enter a string");

        System.out.println("--------------------------");

        System.out.println("Integer value: " + userInt);
        System.out.println("Long value: " + userLong);
        System.out.println("Double value: " + userDouble);
        System.out.println("Float value: " + userFloat);
        System.out.println("String value: " + userString);
    }
}
