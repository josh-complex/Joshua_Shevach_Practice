package com.company;

import java.util.Scanner;

public class EvenOrOdd {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number to check");
        int numberToCheck = Integer.parseInt(scanner.nextLine());

        if (numberToCheck % 2 == 0)
            System.out.println("EVEN");
        else
            System.out.println("ODD");

    }
}
