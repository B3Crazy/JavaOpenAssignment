package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    // Override the start method of the Application class
    @Override
    public void start(Stage primaryStage) {
        try {
            // Print a message indicating the FXML file is being loaded
            System.out.println("Loading FXML file...");

            // Load the FXML file to create the UI
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/userInterface.fxml"));

            // Create a new Scene with the loaded FXML root
            Scene scene = new Scene(root);

            // Add the stylesheet to the scene
            scene.getStylesheets().add(getClass().getResource("/com/example/styles.css").toExternalForm());

            // Print a message indicating the FXML file was loaded successfully
            System.out.println("FXML file loaded successfully.");

            // Set the title of the primary stage (window)
            primaryStage.setTitle("Calculator");

            // Set the scene of the primary stage
            primaryStage.setScene(scene);

            // Set the width of the primary stage
            primaryStage.setWidth(550);

            // Set the height of the primary stage
            primaryStage.setHeight(500);

            // Make the primary stage non-resizable
            primaryStage.setResizable(false);

            // Show the primary stage
            primaryStage.show();

            // Print a message indicating the application started successfully
            System.out.println("Application started successfully.");
        } catch (Exception e) {
            // Print an error message if there is an exception
            System.out.println("Error loading FXML file or starting application.");
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }

}