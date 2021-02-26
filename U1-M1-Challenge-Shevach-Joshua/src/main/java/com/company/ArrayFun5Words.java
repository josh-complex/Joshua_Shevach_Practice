package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayFun5Words {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a word");
        String one = scan.nextLine();
        System.out.println("Please enter another word");
        String two = scan.nextLine();
        System.out.println("Please enter another word");
        String three = scan.nextLine();
        System.out.println("Please enter another word");
        String four = scan.nextLine();
        System.out.println("Please enter a final word");
        String five = scan.nextLine();

        String[] strings = new String[] { one, two, three, four, five };
        System.out.println("The elements in the array are: " + Arrays.toString(strings));
    }

}
