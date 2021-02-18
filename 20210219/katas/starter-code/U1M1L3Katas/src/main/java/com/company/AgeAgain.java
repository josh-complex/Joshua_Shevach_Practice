package com.company;

import java.util.Scanner;

public class AgeAgain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What's your age?");
        int age = Integer.parseInt(scanner.nextLine());

        if (age < 14) {
            System.out.println("What grade are you in?");
            String grade = scanner.nextLine();
            System.out.printf("Wow! %s grade - that sounds exciting!", grade);
        } else if (age >= 14 && age <= 18) {
            System.out.println("Are you planning to go to college?");
            String answer = scanner.nextLine();
            if (answer.equals("yes")) {
                System.out.println("What college?");
                String college = scanner.nextLine();
                System.out.printf("%s is a great school!", college);
            }
            else if (answer.equals("no")){
                System.out.println("What do you want to do after high school?");
                String afterHighSchool = scanner.nextLine();
                System.out.printf("Wow, %s sounds like a plan!", afterHighSchool);
            }
        } else if (age > 18){
            System.out.println("What is your job?");
            String job = scanner.nextLine();
            System.out.printf("%s sounds like a great job!", job);;
        }
    }
}
