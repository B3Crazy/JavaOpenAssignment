package com.example;

// Class to represent a triangle, implementing the ShapeOperation interface
public class Triangle implements ShapeOperation {
    private double base;
    private double height;

    // Setter method for base
    public void setBase(double base) {
        this.base = base;
    }

    // Setter method for height
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double calculate() {
        return 0.5 * base * height;
    }
}