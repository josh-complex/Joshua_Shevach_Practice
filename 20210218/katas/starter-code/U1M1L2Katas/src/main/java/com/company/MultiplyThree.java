package com.company;

import java.util.Scanner;

public class MultiplyThree {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double numberOne = Double.parseDouble(scanner.nextLine());
        double numberTwo = Double.parseDouble(scanner.nextLine());
        double numberThree = Double.parseDouble(scanner.nextLine());

        System.out.println(numberOne * numberTwo * numberThree);

        scanner.close();

    }
}