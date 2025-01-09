package com.example;
public class SubtractionOperation extends CalculatorOperation {
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 - operand2;
    }
}