package com.company;

import com.company.interfaces.Shape;

public class Square implements Shape {

    double sideLength;

    public Square(){ }

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double perimeter() {
        return sideLength * 4;
    }

    @Override
    public double area() {
        return Math.pow(sideLength, 2);
    }
}
