package com.example;
public class DivisionOperation extends CalculatorOperation {
    @Override
    public double calculate(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return operand1 / operand2;
    }
}