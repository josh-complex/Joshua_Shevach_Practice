package com.company;

import java.util.Scanner;

public class YourAgeCan {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your age");
        int age = Integer.parseInt(scanner.nextLine());

        if (age >= 18){
            System.out.println("You can vote");
        }
        if (age >= 21){
            System.out.println("You can drink alcohol");
        }
        if (age >= 35){
            System.out.println("You can be president");
        }
        if (age >= 55){
            System.out.println("You are eligible for AARP");
        }
        if (age >= 65){
            System.out.println("You can retire");
        }
        if (age >= 80 && age <= 89){
            System.out.println("You are an octogenarian");
        }
        if (age >= 100){
            System.out.println("You are more than a century old");
        }

    }
}
