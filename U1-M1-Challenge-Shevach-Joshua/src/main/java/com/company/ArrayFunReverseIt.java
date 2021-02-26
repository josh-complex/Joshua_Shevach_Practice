package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayFunReverseIt {

    public static void main(String[] args) {
        int[] integers = new int[] { 1, 2, 3, 4, 5 };
        int[] reversed = new int[integers.length];

        int index = integers.length - 1;
        for(int itr : integers){
            reversed[index] = itr;
            index--;
        }

        System.out.println("The elements in the array are: " + Arrays.toString(reversed));
    }

}
