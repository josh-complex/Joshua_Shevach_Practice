package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayFunUserArray {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter an integer.");
        int one = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter another integer.");
        int two = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter another integer.");
        int three = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter another integer.");
        int four = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter a final integer.");
        int five = Integer.parseInt(scan.nextLine());

        int[] integers = new int[] { one, two, three, four, five };
        System.out.println("The elements in the array are: " + Arrays.toString(integers));
    }

}
