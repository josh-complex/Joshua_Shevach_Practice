package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeFinder {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberToCheck = Integer.parseInt(scanner.nextLine());

        List<Integer> notPrime = new ArrayList<Integer>();

        System.out.println(2);

        for (int i = 2; i <= numberToCheck; i++) {

            for (int j = 2; j <= i; j++) {
                if(j == i){
                    System.out.println(j);
                    continue;
                }
                if(i % j == 0){
                    break;
                }
            }

        }

    }
}
