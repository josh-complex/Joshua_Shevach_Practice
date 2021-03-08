package com.company;

public class Calculator {

    //region Add
    public int add(int a, int b){
        int result = a + b;
        System.out.printf("The result of adding %d and %d is %d%n", a, b, result);
        return result;
    }
    public double add(double a, double b){
        double result = a + b;
        System.out.printf("The result of adding %.3f and %.3f is %.3f%n", a, b, result);
        return result;
    }
    //endregion

    //region Subtract
    public int subtract(int a, int b){
        int result = a - b;
        System.out.printf("The result of subtracting %d from %d is %d%n", b, a, result);
        return result;
    }
    public double subtract(double a, double b){
        double result = a - b;
        System.out.printf("The result of subtracting %.3f from %.3f is %.3f%n", b, a, result);
        return result;
    }
    //endregion

    //region Multiply
    public int multiply(int a, int b){
        int result = a * b;
        System.out.printf("The result of multiplying %d and %d is %d%n", a, b, result);
        return result;
    }
    public double multiply(double a, double b){
        double result = a * b;
        System.out.printf("The result of multiplying %.3f and %.3f is %.3f%n", a, b, result);
        return result;
    }
    //endregion

    //region Divide
    public int divide(int a, int b){
        int result = a / b;
        System.out.printf("The result of dividing %d by %d is %d%n", a, b, result);
        return result;
    }
    public double divide(double a, double b){
        double result = a / b;
        System.out.printf("The result of dividing %.3f by %.3f is %.3f%n", a, b, result);
        return result;
    }
    //endregion
}
