package com.company;

import java.util.Scanner;

public class CommandLineGram {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your first name");
        String firstName = scanner.nextLine();
        System.out.println(firstName);

        System.out.println("Please enter your last name");
        String lastName = scanner.nextLine();
        System.out.println(lastName);

        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        System.out.println(email);

        System.out.println("Please enter your Twitter handle");
        String twitterHandle = scanner.nextLine();
        System.out.println(twitterHandle);

        System.out.println("Please enter your age");
        String age = scanner.nextLine();
        System.out.println(age);

        System.out.println("Please enter your country");
        String country = scanner.nextLine();
        System.out.println(country);

        System.out.println("Please enter your profession");
        String profession = scanner.nextLine();
        System.out.println(profession);

        System.out.println("Please enter your favorite OS");
        String favoriteOS = scanner.nextLine();
        System.out.println(favoriteOS);

        System.out.println("Please enter your favorite programming language");
        String favoriteProgrammingLanguage = scanner.nextLine();
        System.out.println(favoriteProgrammingLanguage);

        System.out.println("Please enter your favorite computer scientist");
        String favoriteComputerScientist = scanner.nextLine();
        System.out.println(favoriteComputerScientist);

        System.out.println("Please enter your favorite keyboard shortcut");
        String favoriteKeyboardShortcut = scanner.nextLine();
        System.out.println(favoriteKeyboardShortcut);

        System.out.println("Have you ever built your own computer?");
        String builtComputer = scanner.nextLine();
        System.out.println(builtComputer);

        System.out.println("If you could be any superhero, who would it be?");
        String superhero = scanner.nextLine();
        System.out.println(superhero);

    }
}
