package com.company;

import java.util.Scanner;

public class LuxuryTaxCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the salary for Player 1");
        double playerOneSalary = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the salary for Player 2");
        double playerTwoSalary = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the salary for Player 3");
        double playerThreeSalary = Double.parseDouble(scanner.nextLine());

        double totalSalary = playerOneSalary + playerTwoSalary + playerThreeSalary;

        System.out.printf("The total salary of all three players is %.2f %n", totalSalary);

        double spendingLimit = 40000000;
        double taxRate = .18;

        if (totalSalary > spendingLimit){
            double limitDifference = totalSalary - spendingLimit;
            double luxuryTax = taxRate * limitDifference;

            System.out.printf("The luxury tax on these three players will be %.2f %n", luxuryTax);
        }

    }
}
