package com.example;

// Import necessary JavaFX and utility classes
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Controller class for handling the user interface
public class UserInterfaceController {

    // FXML annotations to link UI components
    @FXML
    private TextField display; // TextField to display the current input and result
    @FXML
    private ListView<Calculation> historyList; // ListView to display the history of calculations

    // List to store the operands for calculations
    private List<Double> operands = new ArrayList<>();
    // List to store the operators for calculations
    private List<String> operators = new ArrayList<>();
    // List to store the history of calculations
    private ObservableList<Calculation> historyItems = FXCollections.observableArrayList();
    // String to store the current operator
    private String currentOperator = "";
    // String to store the current input
    private String currentInput = "";
    // Boolean flag to indicate if the result is shown
    private boolean resultShown = false;

    // Method to initialize the controller
    @FXML
    public void initialize() {
        // Set the items of the historyList to the historyItems
        historyList.setItems(historyItems);
        historyList.setCellFactory(new Callback<ListView<Calculation>, ListCell<Calculation>>() {
            @Override
            public ListCell<Calculation> call(ListView<Calculation> listView) {
                return new ListCell<Calculation>() {
                    @Override
                    protected void updateItem(Calculation item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.toString());
                            setStyle("-fx-background-color: #1a001a; -fx-text-fill: #ffffff;");
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        // Add a mouse click event handler to the historyList
        historyList.setOnMouseClicked(event -> {
            // Check if the click count is 2 (double-click)
            if (event.getClickCount() == 2) {
                // Get the selected calculation from the history list
                Calculation selectedCalculation = historyList.getSelectionModel().getSelectedItem();
                // If a calculation is selected, continue the calculation
                if (selectedCalculation != null) {
                    continueCalculation(selectedCalculation);
                }
            }
        });
    }

    // Method to handle button actions
    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        // Get the button that was clicked
        Button button = (Button) event.getSource();
        // Get the text of the button
        String buttonText = button.getText();

        // If the result is shown and the button is not an operator, reset the input and
        // operands
        if (resultShown && !isOperator(buttonText)) {
            currentInput = "";
            operands.clear();
            operators.clear();
            currentOperator = "";
            resultShown = false;
        }

        // If the button is an operator, process the operator
        if (isOperator(buttonText)) {
            // If there is current input, add it to the operands list
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput));
                currentInput = "";
            }
            // Set the current operator to the button text
            currentOperator = buttonText;
            operators.add(currentOperator);
        } else if (buttonText.equals("=")) {
            // If the button is "=", calculate the result
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput));
                calculateResult();
            }
        } else {
            // If the button is a number or decimal point, append it to the current input
            currentInput += buttonText;
            display.setText(currentInput);
        }
    }

    @FXML
    private void handleMemoryClear() {
        historyItems.clear();
        System.out.println("Memory cleared");
    }

    @FXML
    private void handleClear() {
        currentInput = "";
        operands.clear();
        operators.clear();
        currentOperator = "";
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

    // Method to check if a string is an operator
    private boolean isOperator(String text) {
        return text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/");
    }

    // Method to calculate the result of the current operation
    private void calculateResult() {
        try {
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

            // Create a new Calculation object with the current operands, operators, and
            // result
            Calculation calculation = new Calculation(new ArrayList<>(operands), new ArrayList<>(operators));

            // Add the new calculation to the top of the history list
            historyItems.add(0, calculation);

            // Display the result in the UI
            display.setText(String.valueOf(result));

            // Update the current input to the result for the next calculation
            currentInput = String.valueOf(result);

            // Clear the operands and operators lists and add the result as the only operand
            // for the next calculation
            operands.clear();
            operators.clear();
            operands.add(result); // Only the result is used for the next calculation

            // Reset the current operator and set the resultShown flag to true
            currentOperator = "";
            resultShown = true;

        } catch (NumberFormatException e) {
            // Handle the exception
            display.setText("Error: Invalid number format");
        } catch (ArithmeticException e) {
            // Catch block for handling arithmetic exceptions (e.g., division by zero)
            display.setText("Error: Arithmetic error");
        } catch (Exception e) {
            // Catch block for handling any other exceptions
            display.setText("Error: " + e.getMessage());
        }
    }

    // Method to handle showing the calculation history
    @FXML
    private void handleShowHistory() {
        // Sort the history items, convert them to strings, and collect them into an
        // observable list
        ObservableList<String> sortedItems = historyItems.stream()
                .sorted()
                .map(Calculation::toString)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Create a ListView to display the sorted items
        ListView<String> sortedListView = new ListView<>(sortedItems);

        // Set a custom cell factory to style the ListView items
        sortedListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            // Set the text of the ListView item
                            setText(item);
                            // Apply custom styles to the ListView item
                            setStyle("-fx-background-color: #1a001a; -fx-text-fill: #ffffff;");
                        } else {
                            // Clear the text if the item is null
                            setText(null);
                        }
                    }
                };
            }
        });

        // Create a VBox layout and add the sortedListView to it
        VBox vbox = new VBox(sortedListView);

        // Create a new Scene with the VBox layout, setting its width and height
        Scene scene = new Scene(vbox, 300, 400);

        // Create a new Stage (window) for displaying the sorted calculations
        Stage stage = new Stage();
        stage.setTitle("Sorted Calculations"); // Set the title of the window
        stage.setScene(scene); // Set the scene to the stage
        stage.show(); // Display the stage
    }

    // Method to continue a calculation based on a previous result
    private void continueCalculation(Calculation calculation) {
        // Clear the operands list
        operands.clear();
        // Add the result of the previous calculation as the only operand
        operands.add(calculation.getResult()); // Only the result is used for the next calculation
        // Reset the current operator and input
        currentOperator = "";
        currentInput = "";
        // Display the result of the previous calculation
        display.setText(String.valueOf(calculation.getResult()));
        // Set the resultShown flag to false to allow further calculations
        resultShown = false;
    }

    // Method to sort and display calculations
    public void displaySortedCalculations() {
        ObservableList<String> sortedItems = historyItems.stream()
                .sorted() // This uses the compareTo method in Calculation
                .map(Calculation::toString)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Create a ListView to display the sorted items
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
}
