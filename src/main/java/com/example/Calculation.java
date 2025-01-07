package com.example;

import java.util.List;

// Class to represent a calculation
public class Calculation implements Comparable<Calculation> {

    // List to store the operands of the calculation
    private List<Double> operands;
    // List to store the operators of the calculation
    private List<String> operators;
    // Double to store the result of the calculation
    private double result;

    // Constructor to initialize the operands, operators, and result
    public Calculation(List<Double> operands, List<String> operators) {
        this.operands = operands;
        this.operators = operators;
        this.result = performCalculation();
    }

    // Getter method for operands
    public List<Double> getOperands() {
        return operands;
    }

    // Getter method for operators
    public List<String> getOperators() {
        return operators;
    }

    // Getter method for result
    public double getResult() {
        return result;
    }

    // Method to perform the calculation based on operands and operators
    private double performCalculation() {
        double result = operands.get(0);
        for (int i = 1; i < operands.size(); i++) {
            String operator = operators.get(i - 1);
            double operand = operands.get(i);
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        return result;
    }

    // Method to compare two Calculation objects based on their result
    @Override
    public int compareTo(Calculation other) {
        return Double.compare(this.result, other.result);
    }

    // Method to return a string representation of the calculation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(operands.get(0));
        for (int i = 1; i < operands.size(); i++) {
            sb.append(" ").append(operators.get(i - 1)).append(" ").append(operands.get(i));
        }
        sb.append(" = ").append(result);
        return sb.toString();
    }
}