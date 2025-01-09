package com.example;

// Class to represent a rectangle, implementing the ShapeOperation interface
public class Rectangle implements ShapeOperation {
    private double width;
    private double height;

    // Setter method for width
    public void setWidth(double width) {
        this.width = width;
    }

    // Setter method for height
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double calculate() {
        return width * height;
    }
}