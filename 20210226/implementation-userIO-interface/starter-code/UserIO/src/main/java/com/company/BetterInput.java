package com.company;

import com.company.interfaces.UserIO;

import java.util.Scanner;

public class BetterInput implements UserIO {

    @Override
    public int readInt(String prompt) {
        Scanner scan = new Scanner(System.in);

        int result = 0;

        while (true) {
            try {
                System.out.println(prompt);
                result = Integer.parseInt(scan.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("There was an input error: " + e.toString());
            }
        }

        return result;
    }

    @Override
    public long readLong(String prompt) {
        Scanner scan = new Scanner(System.in);

        long result = 0;

        while (true) {
            try {
                System.out.println(prompt);
                result = Long.parseLong(scan.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("There was an input error: " + e.toString());
            }
        }

        return result;
    }

    @Override
    public double readDouble(String prompt) {
        Scanner scan = new Scanner(System.in);

        double result = 0;

        while (true) {
            try {
                System.out.println(prompt);
                result = Double.parseDouble(scan.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("There was an input error: " + e.toString());
            }
        }

        return result;
    }

    @Override
    public float readFloat(String prompt) {
        Scanner scan = new Scanner(System.in);

        float result = 0;

        while (true) {
            try {
                System.out.println(prompt);
                result = Float.parseFloat(scan.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("There was an input error: " + e.toString());
            }
        }

        return result;
    }

    @Override
    public String readString(String prompt) {
        Scanner scan = new Scanner(System.in);

        String result = "";

        while (true) {
            try {
                System.out.println(prompt);
                result = scan.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("There was an input error: " + e.toString());
            }
        }

        return result;
    }
}
