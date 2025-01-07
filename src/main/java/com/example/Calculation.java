package com.example;

import java.util.List;

// Class to represent a calculation
public class Calculation implements Comparable<Calculation> {

    // List to store the operands of the calculation
    private List<Double> operands;
    // String to store the operator of the calculation
    private String operator;
    // Double to store the result of the calculation
    private double result;

    // Constructor to initialize the operands, operator, and result
    public Calculation(List<Double> operands, String operator, double result) {
        this.operands = operands;
        this.operator = operator;
        this.result = result;
    }

    // Getter method for operands
    public List<Double> getOperands() {
        return operands;
    }

    // Getter method for operator
    public String getOperator() {
        return operator;
    }

    // Getter method for result
    public double getResult() {
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
        for (int i = 0; i < operands.size(); i++) {
            sb.append(operands.get(i));
            if (i < operands.size() - 1) {
                sb.append(" ").append(operator).append(" ");
            }
        }
        sb.append(" = ").append(result);
        return sb.toString();
    }
}