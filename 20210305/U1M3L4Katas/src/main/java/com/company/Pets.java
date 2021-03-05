package com.company;

import java.io.*;
import java.util.Arrays;

import java.util.Scanner;


public class Pets {

    private static String[] petsList = {"0: Dog", "1: Cat", "2: Bird", "3: Snake", "4: Hamster", "5: Rabbit"};
    private static Scanner scan = new Scanner(System.in);
    private static String output = "pets.txt";

    public static String retrievePet(int index) throws ArrayIndexOutOfBoundsException {
        return petsList[index];
    }

    public static int choosePet() throws NumberFormatException {
        System.out.format("Here's a list of all the pets: %s: ", Arrays.asList(petsList));
        return Integer.parseInt(scan.nextLine());
    }

    public static void writePetToFile(String pet) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(output));
        out.println(pet);
        out.flush();
        out.close();
    }

    public static void readPetsFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(output)));

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
        }

        sc.close();
    }
}
