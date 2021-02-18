package com.company;

import java.util.Scanner;

public class RangeChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a number between 15 and 32 inclusive");

        int numberToCheck = 0;

        while (true) {
            try {
                numberToCheck = Integer.parseInt(scanner.nextLine());

                if (numberToCheck >= 15 && numberToCheck <= 32) {
                    break;
                } else {
                    System.out.println("Number not within range");
                }

            } catch (NumberFormatException ignored) {

            }
        }

        System.out.println(numberToCheck);

    }
}
