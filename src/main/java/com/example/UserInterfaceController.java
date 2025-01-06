package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class UserInterfaceController {

    @FXML
    private TextField display;

    @FXML
    private ListView<String> historyList;

    private ObservableList<String> historyItems = FXCollections.observableArrayList();

    private String currentInput = "";
    private CalculatorOperation currentOperation;
    private double firstOperand = 0;
    private boolean resultShown = false;

    @FXML
    public void initialize() {
        historyList.setItems(historyItems);
        historyList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setStyle("-fx-background-color: #1a001a; -fx-text-fill: #ffffff;");
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (resultShown && !isOperator(buttonText)) {
            currentInput = "";
            resultShown = false;
        }

        switch (buttonText) {
            case "=":
                calculateResult();
                break;
            case "+":
                setOperation(new AdditionOperation());
                break;
            case "-":
                setOperation(new SubtractionOperation());
                break;
            case "*":
                setOperation(new MultiplicationOperation());
                break;
            case "/":
                setOperation(new DivisionOperation());
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
        currentOperation = null;
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

    private void setOperation(CalculatorOperation operation) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            currentOperation = operation;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && currentOperation != null) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = currentOperation.calculate(firstOperand, secondOperand);

            String calculation = firstOperand + " " + getOperatorSymbol(currentOperation) + " " + secondOperand + " = " + result;
            historyItems.add(0, calculation); // Add new calculation at the top

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            currentOperation = null;
            resultShown = true;
        }
    }

    private String getOperatorSymbol(CalculatorOperation operation) {
        if (operation instanceof AdditionOperation) {
            return "+";
        } else if (operation instanceof SubtractionOperation) {
            return "-";
        } else if (operation instanceof MultiplicationOperation) {
            return "*";
        } else if (operation instanceof DivisionOperation) {
            return "/";
        }
        return "";
    }

    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }
}
