package com.example;

// Class to represent the multiplication operation, implementing the CalculatorOperation interface
public class MultiplicationOperation implements CalculatorOperation {

    // Override the calculate method to perform multiplication
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 * operand2; // Return the result of multiplying operand1 by operand2
    }
}