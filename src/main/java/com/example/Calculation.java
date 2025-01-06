package com.example;

import java.util.List;

public class Calculation implements Comparable<Calculation> {
    private final List<Double> operands;
    private final String operator;
    private final double result;

    public Calculation(List<Double> operands, String operator, double result) {
        this.operands = operands;
        this.operator = operator;
        this.result = result;
    }

    public List<Double> getOperands() {
        return operands;
    }

    public String getOperator() {
        return operator;
    }

    public double getResult() {
        return result;
    }

    @Override
    public int compareTo(Calculation other) {
        return Double.compare(this.result, other.result);
    }

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