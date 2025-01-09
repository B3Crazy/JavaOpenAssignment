package com.example;

import java.util.List;

// Class to represent the addition operation, extending the CalculatorOperation abstract class
public class AdditionOperation extends CalculatorOperation {
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 + operand2;
    }
}