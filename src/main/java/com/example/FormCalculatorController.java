package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormCalculatorController {
    @FXML
    private TextField radiusField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField baseField;
    @FXML
    private TextField triangleHeightField;
    @FXML
    private Label resultLabel;

    @FXML
    private void handleCalculateCircleArea() {
        double radius = Double.parseDouble(radiusField.getText());
        ShapeOperation circle = new Circle();
        ((Circle) circle).setRadius(radius);
        double area = circle.calculate();
        resultLabel.setText("Circle Area: " + area);
    }

    @FXML
    private void handleCalculateRectangleArea() {
        double width = Double.parseDouble(widthField.getText());
        double height = Double.parseDouble(heightField.getText());
        ShapeOperation rectangle = new Rectangle();
        ((Rectangle) rectangle).setWidth(width);
        ((Rectangle) rectangle).setHeight(height);
        double area = rectangle.calculate();
        resultLabel.setText("Rectangle Area: " + area);
    }

    @FXML
    private void handleCalculateTriangleArea() {
        double base = Double.parseDouble(baseField.getText());
        double height = Double.parseDouble(triangleHeightField.getText());
        ShapeOperation triangle = new Triangle();
        ((Triangle) triangle).setBase(base);
        ((Triangle) triangle).setHeight(height);
        double area = triangle.calculate();
        resultLabel.setText("Triangle Area: " + area);
    }
}