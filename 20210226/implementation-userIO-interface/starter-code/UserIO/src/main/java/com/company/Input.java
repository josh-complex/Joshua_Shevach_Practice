package com.company;

import com.company.interfaces.UserIO;

import java.util.Scanner;

public class Input implements UserIO {

    @Override
    public int readInt(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        return Integer.parseInt(scan.nextLine());
    }

    @Override
    public long readLong(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        return Long.parseLong(scan.nextLine());
    }

    @Override
    public double readDouble(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        return Double.parseDouble(scan.nextLine());
    }

    @Override
    public float readFloat(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        return Float.parseFloat(scan.nextLine());
    }

    @Override
    public String readString(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        return scan.nextLine();
    }
}
