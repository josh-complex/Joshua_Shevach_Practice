package com.company;

import java.util.Scanner;
import java.lang.Math;

public class LoanCalculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double mortgageAmount = Double.parseDouble(scanner.nextLine());
        double mortgageTerm = Double.parseDouble(scanner.nextLine());
        double mortgageInterestRate = Double.parseDouble(scanner.nextLine());

        double mortgageMonthlyInterest = (mortgageInterestRate / 100) / 12;

        double calcTop = mortgageMonthlyInterest * Math.pow(1 + mortgageMonthlyInterest, mortgageTerm);
        double calcBottom = Math.pow(1 + mortgageMonthlyInterest, mortgageTerm) - 1;

        double mortgageMonthlyPayment = mortgageAmount * ( calcTop / calcBottom );

        System.out.println(mortgageMonthlyPayment);

    }
}
