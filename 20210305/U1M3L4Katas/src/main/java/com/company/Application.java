package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        System.out.println("Current saved animals file:");

        try {
            Pets.readPetsFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("The following file does not seem to exist: " + e.getMessage());
        } finally {
            System.out.println("Have a nice day!");
        }

        int petIndex = 0;
        try {
            petIndex = Pets.choosePet();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } finally {
            System.out.println("Have a nice day!");
        }

        String chosenPet = "";
        try {
            chosenPet = Pets.retrievePet(petIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please choose an available pet.");
        } finally {
            System.out.println("Have a nice day!");
        }

        try {
            Pets.writePetToFile(chosenPet);
        } catch (IOException e) {
            System.out.println("Something went wrong while writing your file.");
        } finally {
            System.out.println("Have a nice day!");
        }

        System.out.println("New saved animals file:");
        try {
            Pets.readPetsFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("The following file does not seem to exist: " + e.getMessage());
        } finally {
            System.out.println("Have a nice day!");
        }

    }
}
