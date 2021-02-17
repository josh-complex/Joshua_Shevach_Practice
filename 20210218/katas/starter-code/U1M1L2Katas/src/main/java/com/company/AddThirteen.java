package com.company;

import java.util.Scanner;

public class AddThirteen {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberOne = Integer.parseInt(scanner.nextLine());

        System.out.println(numberOne + 13);

        scanner.close();

    }
}
