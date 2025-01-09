package com.example;

// Class to represent a circle, implementing the ShapeOperation interface
public class Circle implements ShapeOperation {
    private double radius;

    // Setter method for radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculate() {
        return Math.PI * radius * radius;
    }
}