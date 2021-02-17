package com.company;

import javax.lang.model.type.IntersectionType;
import java.util.Scanner;

public class SubtractTwo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberOne = Integer.parseInt(scanner.nextLine());
        int numberTwo = Integer.parseInt(scanner.nextLine());

        System.out.println(numberOne - numberTwo);

        scanner.close();

    }
}
