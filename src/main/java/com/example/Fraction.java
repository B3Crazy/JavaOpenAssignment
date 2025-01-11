package com.example;

public class Fraction implements Comparable<Fraction> {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public int compareTo(Fraction other) {
        double thisValue = (double) this.numerator / this.denominator;
        double otherValue = (double) other.numerator / other.denominator;
        return Double.compare(thisValue, otherValue);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
