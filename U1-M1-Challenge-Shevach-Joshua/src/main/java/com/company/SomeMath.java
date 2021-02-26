package com.company;

public class SomeMath {

    public static void main(String[] args) {
        System.out.println(total5(1, 2, 3, 4, 5));
        System.out.println(average5(1, 3, 5, 7,9));
        System.out.println(largest5(42.0, 35.1, 2.3, 40.2, 15.6));
    }

    private static int total5(int one, int two, int three, int four, int five){
        return one + two + three + four + five;
    }

    private static double average5(int one, int two, int three, int four, int five) {
        return (one + two + three + four + five)/5.0;
    }

    private static double largest5(double one, double two, double three, double four, double five){
        return Math.max(one, Math.max(two, Math.max(three, Math.max(four, five))));
    }

}
