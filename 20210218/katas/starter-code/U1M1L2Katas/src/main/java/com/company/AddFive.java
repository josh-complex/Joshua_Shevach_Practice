package com.company;

import java.util.Scanner;

public class AddFive {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double numberOne = Double.parseDouble(scanner.nextLine());
        double numberTwo = Double.parseDouble(scanner.nextLine());
        double numberThree = Double.parseDouble(scanner.nextLine());
        double numberFour = Double.parseDouble(scanner.nextLine());
        double numberFive = Double.parseDouble(scanner.nextLine());

        System.out.println(numberOne + numberTwo + numberThree + numberFour + numberFive);

        scanner.close();

    }
}
