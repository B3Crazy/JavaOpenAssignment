package com.example;

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

public class UserInterfaceController {

    @FXML
    private TextField display;

    @FXML
    private ListView<Calculation> historyList;

    private ObservableList<Calculation> historyItems = FXCollections.observableArrayList();

    private String currentInput = "";
    private List<Double> operands = new ArrayList<>();
    private String currentOperator = "";
    private boolean resultShown = false;

    @FXML
    public void initialize() {
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
    }

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (resultShown && !isOperator(buttonText)) {
            currentInput = "";
            operands.clear();
            currentOperator = "";
            resultShown = false;
        }

        if (isOperator(buttonText)) {
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput));
                currentInput = "";
            }
            currentOperator = buttonText;
        } else if (buttonText.equals("=")) {
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput));
                calculateResult();
            }
        } else {
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

    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }

    private void calculateResult() {
        try {
            double result = operands.get(0);
            for (int i = 1; i < operands.size(); i++) {
                switch (currentOperator) {
                    case "+":
                        result += operands.get(i);
                        break;
                    case "-":
                        result -= operands.get(i);
                        break;
                    case "*":
                        result *= operands.get(i);
                        break;
                    case "/":
                        result /= operands.get(i);
                        break;
                }
            }

            Calculation calculation = new Calculation(new ArrayList<>(operands), currentOperator, result);
            historyItems.add(0, calculation); // Add new calculation at the top

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operands.clear();
            operands.add(result);
            currentOperator = "";
            resultShown = true;
        } catch (NumberFormatException e) {
            display.setText("Error: Invalid number format");
        } catch (ArithmeticException e) {
            display.setText("Error: Arithmetic error");
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleShowHistory() {
        ObservableList<String> sortedItems = historyItems.stream()
            .sorted()
            .map(Calculation::toString)
            .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ListView<String> sortedListView = new ListView<>(sortedItems);
        sortedListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
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

        VBox vbox = new VBox(sortedListView);
        Scene scene = new Scene(vbox, 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Sorted Calculations");
        stage.setScene(scene);
        stage.show();
    }
}
