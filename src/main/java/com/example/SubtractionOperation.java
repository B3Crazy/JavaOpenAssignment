package com.example;

// Class to represent the subtraction operation, implementing the CalculatorOperation interface
public class SubtractionOperation implements CalculatorOperation {

    // Override the calculate method to perform subtraction
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 - operand2; // Return the result of subtracting operand2 from operand1
    }
}