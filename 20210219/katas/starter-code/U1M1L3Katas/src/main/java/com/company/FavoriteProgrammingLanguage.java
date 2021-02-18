package com.company;

import java.util.Scanner;

public class FavoriteProgrammingLanguage {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String myFavoriteLanguage = "";

        while (true) {
            System.out.println("Hello user, guess your favorite programming language!");
            myFavoriteLanguage = scanner.nextLine();

            if (myFavoriteLanguage.equals("Java")) {
                System.out.println("That's what I was looking for! Java is definitely the answer!");
                break;
            }
        }

    }
}
