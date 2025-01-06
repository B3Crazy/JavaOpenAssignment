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

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Loading FXML file...");
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/userInterface.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/styles.css").toExternalForm());
            System.out.println("FXML file loaded successfully.");
            primaryStage.setTitle("Calculator");
            primaryStage.setScene(scene);
            primaryStage.setWidth(550);
            primaryStage.setHeight(500);
            primaryStage.setResizable(false);
            primaryStage.show();
            System.out.println("Application started successfully.");
        } catch (Exception e) {
            System.out.println("Error loading FXML file or starting application.");
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}