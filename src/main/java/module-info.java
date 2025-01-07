// Define the module for the application
module com.example {
    // Require the JavaFX controls module
    requires javafx.controls;
    // Require the JavaFX FXML module
    requires javafx.fxml;

    // Open the com.example package to the JavaFX FXML module for reflection
    opens com.example to javafx.fxml;
    // Export the com.example package to make it accessible to other modules
    exports com.example;
}
