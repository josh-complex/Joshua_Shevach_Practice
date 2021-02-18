package com.company;

import java.util.Scanner;

public class CountByThirteen {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number to count up to");
        int numberToCountTo = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <= numberToCountTo; i += 13)
            System.out.println(i);

    }
}