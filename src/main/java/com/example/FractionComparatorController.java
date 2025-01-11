package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FractionComparatorController {
    @FXML
    private TextField numerator1Field;
    @FXML
    private TextField denominator1Field;
    @FXML
    private TextField numerator2Field;
    @FXML
    private TextField denominator2Field;
    @FXML
    private Label resultLabel;

    @FXML
    private void handleCompareFractions() {
        try {
            int numerator1 = Integer.parseInt(numerator1Field.getText());
            int denominator1 = Integer.parseInt(denominator1Field.getText());
            int numerator2 = Integer.parseInt(numerator2Field.getText());
            int denominator2 = Integer.parseInt(denominator2Field.getText());

            Fraction fraction1 = new Fraction(numerator1, denominator1);
            Fraction fraction2 = new Fraction(numerator2, denominator2);

            int comparison = fraction1.compareTo(fraction2);
            if (comparison > 0) {
                resultLabel.setText(fraction1 + " is greater than " + fraction2);
            } else if (comparison < 0) {
                resultLabel.setText(fraction1 + " is less than " + fraction2);
            } else {
                resultLabel.setText(fraction1 + " is equal to " + fraction2);
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Error: Invalid number format");
        } catch (IllegalArgumentException e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }
}
