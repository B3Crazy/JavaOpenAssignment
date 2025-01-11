package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterfaceController {
    @FXML
    private TextField operand1Field;
    @FXML
    private TextField operand2Field;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField display;
    @FXML
    private ListView<String> historyListView;

    private List<Double> operands = new ArrayList<>();
    private List<String> operators = new ArrayList<>();
    private ObservableList<String> historyItems = FXCollections.observableArrayList();
    private String currentInput = "";
    private String currentOperator = "";
    private boolean resultShown = false;

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        String buttonText = ((javafx.scene.control.Button) event.getSource()).getText();

        if ("0123456789.".contains(buttonText)) {
            // If the button is a number or a decimal point, append it to the current input
            if (resultShown) {
                currentInput = "";
                resultShown = false;
            }
            currentInput += buttonText;
            display.setText(currentInput);
        } else if ("+-*/".contains(buttonText)) {
            // If the button is an operator, process the current input and operator
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput));
                currentInput = "";
            }
            if (!operators.isEmpty() && resultShown) {
                operators.set(operators.size() - 1, buttonText);
            } else {
                operators.add(buttonText);
            }
            currentOperator = buttonText;
            resultShown = false;
        } else if ("=".equals(buttonText)) {
            // If the button is the equals sign, process the current input and calculate the
            // result
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput));
                currentInput = "";
            }
            handleEqualsAction();
        } else if ("C".equals(buttonText)) {
            // If the button is the clear button, clear the current input and reset the
            // calculator
            handleClear();
        } else if ("DEL".equals(buttonText)) {
            // If the button is the delete button, delete the last character of the current
            // input
            handleDelete();
        } else if ("OFF".equals(buttonText)) {
            // If the button is the off button, handle the off action
            handleOff();
        } else if ("HIST".equals(buttonText)) {
            // If the button is the history button, show the history
            handleShowHistory();
        }
    }

    @FXML
    private void handleEqualsAction() {
        try {
            if (operands.size() < 2 || operators.size() < 1) {
                throw new IllegalArgumentException("Insufficient operands or operators");
            }

            double result = operands.get(0);
            for (int i = 1; i < operands.size(); i++) {
                String operator = operators.get(i - 1);
                CalculatorOperation operation;
                switch (operator) {
                    case "+":
                        operation = new AdditionOperation();
                        break;
                    case "-":
                        operation = new SubtractionOperation();
                        break;
                    case "*":
                        operation = new MultiplicationOperation();
                        break;
                    case "/":
                        operation = new DivisionOperation();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + operator);
                }
                result = operation.calculate(result, operands.get(i));
            }

            // Create a new Calculation object with the current operands, operators, and
            // result
            StringBuilder calculationBuilder = new StringBuilder();
            for (int i = 0; i < operands.size(); i++) {
                calculationBuilder.append(operands.get(i));
                if (i < operators.size()) {
                    calculationBuilder.append(" ").append(operators.get(i)).append(" ");
                }
            }
            calculationBuilder.append(" = ").append(result);
            String calculation = calculationBuilder.toString();

            // Add the new calculation to the top of the history list
            historyItems.add(0, calculation);

            // Display the result in the UI
            display.setText(String.valueOf(result));

            // Update the historyListView with the latest history items
            historyListView.setItems(historyItems);

            // Clear the operands and operators lists and reset the current input and
            // operator
            operands.clear();
            operators.clear();
            currentInput = "";
            currentOperator = "";
            resultShown = false;

        } catch (NumberFormatException e) {
            // Handle the exception
            display.setText("Error: Invalid number format");
        } catch (ArithmeticException e) {
            // Handle the exception
            display.setText("Error: Arithmetic exception");
        } catch (IllegalArgumentException e) {
            // Handle the exception
            display.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdditionOperation() {
        double operand1 = Double.parseDouble(operand1Field.getText());
        double operand2 = Double.parseDouble(operand2Field.getText());
        CalculatorOperation addition = new AdditionOperation();
        double result = addition.calculate(operand1, operand2);
        resultLabel.setText("Addition Result: " + result);
    }

    @FXML
    private void handleSubtractionOperation() {
        double operand1 = Double.parseDouble(operand1Field.getText());
        double operand2 = Double.parseDouble(operand2Field.getText());
        CalculatorOperation subtraction = new SubtractionOperation();
        double result = subtraction.calculate(operand1, operand2);
        resultLabel.setText("Subtraction Result: " + result);
    }

    @FXML
    private void handleMultiplicationOperation() {
        double operand1 = Double.parseDouble(operand1Field.getText());
        double operand2 = Double.parseDouble(operand2Field.getText());
        CalculatorOperation multiplication = new MultiplicationOperation();
        double result = multiplication.calculate(operand1, operand2);
        resultLabel.setText("Multiplication Result: " + result);
    }

    @FXML
    private void handleDivisionOperation() {
        double operand1 = Double.parseDouble(operand1Field.getText());
        double operand2 = Double.parseDouble(operand2Field.getText());
        CalculatorOperation division = new DivisionOperation();
        double result = division.calculate(operand1, operand2);
        resultLabel.setText("Division Result: " + result);
    }

    @FXML
    private void handleMemoryClear() {
        // Clear the memory (history) of calculations
        historyItems.clear();
        historyListView.setItems(historyItems);
    }

    @FXML
    private void handleClear() {
        // Clear the current input and reset the calculator
        currentInput = "";
        operands.clear();
        operators.clear();
        display.setText("");
        resultShown = false;
    }

    @FXML
    private void handleDelete() {
        // Delete the last character of the current input
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput);
        }
    }

    @FXML
    private void handleOff() {
        // Handle the off action
        System.exit(0);
    }

    @FXML
    private void handleOpenFormCalculator() {
        try {
            // Load the form calculator FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/formCalculator.fxml"));
            // Create a new scene with the loaded FXML root
            Scene scene = new Scene(root);
            // Add the stylesheet to the scene
            scene.getStylesheets().add(getClass().getResource("/com/example/styles.css").toExternalForm());
            // Create a new stage (window) for the form calculator
            Stage stage = new Stage();
            stage.setTitle("Form Calculator");
            stage.setScene(scene);
            // Make the primary stage non-resizable
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOpenFractionComparator() {
        try {
            // Load the fraction comparator FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/fractionComparator.fxml"));
            // Create a new scene with the loaded FXML root
            Scene scene = new Scene(root);
            // Add the stylesheet to the scene
            scene.getStylesheets().add(getClass().getResource("/com/example/styles.css").toExternalForm());
            // Create a new stage (window) for the fraction comparator
            Stage stage = new Stage();
            stage.setTitle("Fraction Comparator");
            stage.setScene(scene);
            // Make the primary stage non-resizable
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowHistory() {
        // Sort the history items by the end result from lowest to highest
        ObservableList<String> sortedItems = historyItems.stream()
                .sorted(Comparator.comparingDouble(this::extractResult))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Update the historyListView with sorted items
        historyListView.setItems(sortedItems);

        // Create a ListView to display the sorted items in a popup window
        ListView<String> sortedListView = new ListView<>(sortedItems);

        VBox vbox = new VBox(sortedListView);

        // Create a new Scene with the VBox layout, setting its width and height
        Scene scene = new Scene(vbox, 300, 400);

        // Create a new Stage (window) for displaying the sorted calculations
        Stage stage = new Stage();
        stage.setTitle("Sorted Calculations"); // Set the title of the window
        stage.setScene(scene); // Set the scene to the stage
        stage.show(); // Display the stage
    }

    private double extractResult(String calculation) {
        String[] parts = calculation.split(" = ");
        return Double.parseDouble(parts[1]);
    }
}