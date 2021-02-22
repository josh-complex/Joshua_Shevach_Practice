package com.company;

public class App {

    public static int total(int[] intArray) {
        int temp = 0;

        for (int i : intArray)
            temp += i;

        return temp;
    }

    public static int totalOdd(int[] intArray) {
        int temp = 0;

        for (int i = 0; i < intArray.length; i++)
            if (i % 2 == 1)
                temp += intArray[i];

        return temp;
    }

    public static int totalEven(int[] intArray) {
        int temp = 0;

        for (int i = 0; i < intArray.length; i++)
            if (i % 2 == 0)
                temp += intArray[i];

        return temp;
    }

    public static int secondLargestNumber(int[] intArray) {
        int temp = 0;

        for (int i = 0; i < intArray.length; i++) {
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[i] > intArray[j]) {
                    temp = intArray[i];
                    intArray[i] = intArray[j];
                    intArray[j] = temp;
                }
            }

        }

        return intArray[intArray.length - 2];
    }

    public static String[] swapFirstAndLast(String[] stringArray) {
        String temp = stringArray[0];
        stringArray[0] = stringArray[stringArray.length - 1];
        stringArray[stringArray.length - 1] = temp;
        return stringArray;
    }

    public static int[] reverse(int[] intArray) {
        int[] temp = new int[intArray.length];
        int index = 0;

        for (int i = intArray.length - 1; i >= 0; i--) {
            temp[index] = intArray[i];
            index++;
        }

        return temp;
    }

    public static String concatenateString(String[] stringArray) {
        StringBuilder temp = new StringBuilder();

        for (String i : stringArray)
            temp.append(i);

        return temp.toString();
    }

    public static int[] everyThird(int[] intArray) {
        if (intArray.length / 3 < 1)
            return null;

        int[] temp = new int[intArray.length / 3];
        int index = 0;

        for (int i = 0; i < intArray.length; i++) {
            if ((i + 1) % 3 == 0) {
                temp[index] = intArray[i];
                index++;
            }
        }

        return temp;
    }

    public static int[] lessThanFive(int[] intArray) {
        int numberLessThanFive = 0;

        for (int i : intArray)
            if (i < 5)
                numberLessThanFive++;

        if (numberLessThanFive == 0)
            return null;

        int[] temp = new int[numberLessThanFive];
        int index = 0;

        for (int i : intArray) {
            if (i < 5) {
                temp[index] = i;
                index++;
            }
        }

        return temp;
    }

    public static int[][] splitAtFive(int[] intArray) {
        int numbersLessThanFive = 0;

        for (int i : intArray)
            if (i < 5)
                numbersLessThanFive++;

        int[] tempOne = new int[numbersLessThanFive];
        int[] tempTwo = new int[intArray.length - numbersLessThanFive];
        int indexOne = 0;
        int indexTwo = 0;

        for (int i : intArray) {
            if (i < 5) {
                tempOne[indexOne] = i;
                indexOne++;
            }
            else{
                tempTwo[indexTwo] = i;
                indexTwo++;
            }
        }

        return new int[][] { tempOne, tempTwo };
    }

    public static String[][] evensAndOdds(String[] stringArray){
        int evens = stringArray.length / 2;

        if(stringArray.length % 2 == 1)
            evens++;

        String[] tempOne = new String[evens];
        String[] tempTwo = new String[stringArray.length - evens];
        int indexOne = 0;
        int indexTwo = 0;

        for (int i = 0; i < stringArray.length; i++) {
            if(i % 2 == 0){
                tempOne[indexOne] = stringArray[i];
                indexOne++;
            }else{
                tempTwo[indexTwo] = stringArray[i];
                indexTwo++;
            }
        }

        return new String[][] { tempOne, tempTwo };
    }

}
