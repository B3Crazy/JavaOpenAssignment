package com.example;

// Class to represent the multiplication operation, extending the CalculatorOperation abstract class
public class MultiplicationOperation extends CalculatorOperation {
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 * operand2;
    }
}