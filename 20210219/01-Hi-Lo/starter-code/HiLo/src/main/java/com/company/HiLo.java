package com.company;

import java.util.Random;
import java.util.Scanner;

public class HiLo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hi-Low!");

        System.out.println("Enter your name");
        String userName = scanner.nextLine();

        System.out.printf("OK, %s, here are the rules: %n", userName);

        Random random = new Random();
        final int correctNumber = random.nextInt(100) + 1;
        int guessedNumber = 0;

        int numberOfGuesses = 0;

        while(guessedNumber != correctNumber){
            System.out.println("Guess a number between 1 and 100!");
            guessedNumber = Integer.parseInt(scanner.nextLine());

            if(guessedNumber < correctNumber){
                System.out.println("Too low!");
            }
            else{
                System.out.println("Too high!");
            }

            numberOfGuesses++;
        }

        System.out.printf("Congratulations, %s! You win!", userName);
        System.out.printf("It took you %d guesses to find my number!", numberOfGuesses);

    }
}
