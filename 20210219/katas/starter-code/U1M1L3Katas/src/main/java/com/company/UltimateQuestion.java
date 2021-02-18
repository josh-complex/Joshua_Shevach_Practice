package com.company;

import java.util.Random;
import java.util.Scanner;

public class UltimateQuestion {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int myNumber = 0;

        while (true) {
            try {

                System.out.println("Hello user, guess a number between 1 and 100");
                myNumber = Integer.parseInt(scanner.nextLine());

                if (myNumber <= 100 && myNumber >= 1) {
                    if (myNumber == 42) {
                        System.out.println("That's the number I was looking for! 42 is definitely the answer!");
                        break;
                    }
                }
            } catch (NumberFormatException ignored) {

            }
        }
    }
}
