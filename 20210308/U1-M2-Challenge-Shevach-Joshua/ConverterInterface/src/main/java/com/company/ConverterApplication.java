package com.company;

public class ConverterApplication {

    public static void main(String[] args) {
        ConverterIf converterIf = new ConverterIf();
        ConverterSwitch converterSwitch = new ConverterSwitch();

        System.out.println(converterIf.convertDay(6));
        System.out.println(converterIf.convertDay(1));
        System.out.println(converterIf.convertDay(0));
        System.out.println("");
        System.out.println(converterIf.convertMonth(2));
        System.out.println(converterIf.convertMonth(12));
        System.out.println(converterIf.convertMonth(15));
        System.out.println("");
        System.out.println(converterSwitch.convertDay(6));
        System.out.println(converterSwitch.convertDay(1));
        System.out.println(converterSwitch.convertDay(0));
        System.out.println("");
        System.out.println(converterSwitch.convertMonth(2));
        System.out.println(converterSwitch.convertMonth(12));
        System.out.println(converterSwitch.convertMonth(15));
    }

}
