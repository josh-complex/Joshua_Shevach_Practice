package com.company;

import java.util.Scanner;

public class ValidNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a number between 1 and 10.");
        int temp = 0;
        while(true){
            temp = Integer.parseInt(scan.nextLine());
            if(temp >= 1 && temp <= 10){
                System.out.println(temp);
                break;
            }
            System.out.println("You must enter a number between 1 and 10. Please try again.");
        }
    }

}
