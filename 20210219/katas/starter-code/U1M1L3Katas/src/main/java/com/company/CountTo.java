package com.company;

import java.util.Scanner;

public class CountTo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number to count up to");
        int numberToCountTo = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <= numberToCountTo; i++)
            System.out.println(i);

    }
}
