package com.example;

// Class to represent the division operation, extending the CalculatorOperation abstract class
public class DivisionOperation extends CalculatorOperation {
    @Override
    public double calculate(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return operand1 / operand2;
    }
}