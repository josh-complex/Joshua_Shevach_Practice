package com.company;

import java.util.Scanner;

public class MonthConverterIf {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a number between 1 and 12");
        int temp = Integer.parseInt(scan.nextLine());
        if(temp >= 1 && temp <= 12){
            if(temp == 1){
                System.out.println("January");
            } else if(temp == 2){
                System.out.println("February");
            } else if(temp == 3){
                System.out.println("March");
            } else if(temp == 4){
                System.out.println("April");
            } else if(temp == 5){
                System.out.println("May");
            } else if(temp == 6){
                System.out.println("June");
            } else if(temp == 7){
                System.out.println("July");
            } else if(temp == 8){
                System.out.println("August");
            } else if(temp == 9){
                System.out.println("September");
            } else if(temp == 10){
                System.out.println("October");
            } else if(temp == 11){
                System.out.println("November");
            } else {
                System.out.println("December");
            }
        } else {
            System.out.println("You have entered an invalid number. You must enter a number between 1 and 12. Goodbye.");
        }
    }

}
