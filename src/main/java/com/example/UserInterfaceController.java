package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UserInterfaceController {

    @FXML
    private TextField display;

    @FXML
    private ListView<String> historyList;

    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case "=":
                calculateResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            default:
                appendToInput(buttonText);
                break;
        }
    }

    @FXML
    private void handleMemoryClear() {
        // Implement memory clear functionality
        System.out.println("Memory cleared");
    }

    @FXML
    private void handleClear() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        display.setText("");
    }

    @FXML
    private void handleDelete() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput);
        }
    }

    @FXML
    private void handleOff() {
        System.exit(0);
    }

    private void appendToInput(String value) {
        currentInput += value;
        display.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
            }

            String calculation = firstOperand + " " + operator + " " + secondOperand + " = " + result;
            historyList.getItems().add(calculation);

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }
    }
}
