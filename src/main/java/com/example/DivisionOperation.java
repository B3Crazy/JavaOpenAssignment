package com.example;

// Class to represent the division operation, implementing the CalculatorOperation interface
public abstract class DivisionOperation implements CalculatorOperation {

    // Override the calculate method to perform division
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 / operand2; // Return the result of dividing operand1 by operand2
    }
}