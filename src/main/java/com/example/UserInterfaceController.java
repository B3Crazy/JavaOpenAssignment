package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserInterfaceController {

    @FXML
    private TextField nameField;

    @FXML
    private void handleSubmit() {
        String name = nameField.getText();
        System.out.println("Name submitted: " + name);
    }
}
