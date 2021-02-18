package com.company;

import java.util.Random;
import java.util.Scanner;

public class TimesToSeven {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int diceMin = 2;
        int diceMax = 12;

        int currentRoll = 1;
        int rollMax = 100; // default

        int timesToSeven = 0;
        int numberOfSevens = 0;

        System.out.println("Hello user, how many times would you like to roll the dice? (from 1 - 100)");

        while (true){
            try{
                rollMax = Integer.parseInt(scanner.nextLine());

                if(rollMax <= 100 && rollMax >= 1){
                    break;
                }
                else{
                    System.out.println("Invalid! Please enter a number from 1 - 100!");
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a number!");
            }
        }

        while(currentRoll <= rollMax){

            int rollValue = random.nextInt(6) + 1;
            rollValue += random.nextInt(6) + 1;

            if(rollValue == 7) {
                if(timesToSeven == 0){
                    timesToSeven = currentRoll;
                }

                numberOfSevens++;
            }

            System.out.printf("On roll %d, we rolled a %d. It took %d tries to roll the first 7!%n7 has been rolled %d time(s)!%n%n", currentRoll, rollValue, timesToSeven, numberOfSevens);

            currentRoll++;
        }

    }

}
