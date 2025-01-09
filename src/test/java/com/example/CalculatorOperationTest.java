package com.example;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.AdditionOperation;
import com.example.SubtractionOperation;
import com.example.MultiplicationOperation;
import com.example.DivisionOperation;

public class CalculatorOperationTest {
    @Test
    public void testAdditionOperation() {
        AdditionOperation additionOperation = new AdditionOperation();
        double result = additionOperation.calculate(5, 3);
        try {
            assertEquals(8, result, 0);
            System.out.println("Test passed");
        } catch (AssertionError e) {
            System.out.println("Test failed");
            System.out.println(e.getMessage());
            throw e;
        }
        
    }

    @Test
    public void testSubtractionOperation() {
        SubtractionOperation subtractionOperation = new SubtractionOperation();
        double result = subtractionOperation.calculate(5, 3);
        try {
            assertEquals(2, result, 0);
            System.out.println("Test passed");
        } catch (AssertionError e) {
            System.out.println("Test failed");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void testMultiplicationOperation() {
        MultiplicationOperation multiplicationOperation = new MultiplicationOperation();
        double result = multiplicationOperation.calculate(5, 3);
       try {
            assertEquals(15, result, 0);
            System.out.println("Test passed");
        } catch (AssertionError e) {
            System.out.println("Test failed");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void testDivisionOperation() {
        DivisionOperation divisionOperation = new DivisionOperation();
        double result = divisionOperation.calculate(6, 3);
        try {
            assertEquals(2, result, 0);
            System.out.println("Test passed");
        } catch (AssertionError e) {
            System.out.println("Test failed");
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
