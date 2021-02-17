package com.company;

import java.util.Scanner;

public class RectPavingCompany {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the width of the driveway?");
        double width = Double.parseDouble(scanner.nextLine());
        System.out.println("What is the length of the driveway?");
        double length = Double.parseDouble(scanner.nextLine());

        double area = width * length;
        System.out.println("The area of the driveway is " + area);
        double perimeter = (width * 2) + (length * 2);
        System.out.println("The perimeter of the driveway is " + perimeter);

        System.out.println("What is the cost of the cement?");
        double costOfCement = Double.parseDouble(scanner.nextLine());
        System.out.println("What is the cost of the framing?");
        double costOfFraming = Double.parseDouble(scanner.nextLine());

        System.out.printf("The cost of the cement is %.2f %n", costOfCement * area);
        System.out.printf("The cost of the framing is %.2f %n", costOfFraming * perimeter);

    }
}
