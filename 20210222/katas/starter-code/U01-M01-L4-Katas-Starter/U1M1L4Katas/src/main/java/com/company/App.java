/*
* Script Credits: Your Name Here
* References used:
* * https://www.somelink.com/page-with-script-information
*/
package com.company;

public class App {

	// subtract
    public static int subtract(int x, int y){
        return x - y;
    }

	// subtractOrZero
    public static int subtractOrZero(int x, int y){
        return Math.max(x - y, 0);
    }

	// max
    public static int max(int x, int y, int z){
        int firstMax = Math.max(x, y);
        return Math.max(firstMax, z);
    }

	// min
    public static int min(int x, int y, int z){
        int firstMin = Math.min(x, y);
        return Math.min(firstMin, z);
    }

	// isLarger
    public static boolean isLarger(int x, int y){
        return x > y;
    }

}
