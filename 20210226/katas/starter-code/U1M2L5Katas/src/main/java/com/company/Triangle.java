package com.company;

import com.company.interfaces.Shape;

public class Triangle implements Shape {

    private double sideOne;
    private double sideTwo;
    private double base;
    private double height;

    public Triangle() { }

    public Triangle(double sideOne, double sideTwo, double base, double height) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.base = base;
        this.height = height;
    }

    public double getSideOne() {
        return sideOne;
    }

    public void setSideOne(double sideOne) {
        this.sideOne = sideOne;
    }

    public double getSideTwo() {
        return sideTwo;
    }

    public void setSideTwo(double sideTwo) {
        this.sideTwo = sideTwo;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double perimeter() {
        return sideOne + sideTwo + base;
    }

    @Override
    public double area() {
        return (base * height)/2;
    }
}
