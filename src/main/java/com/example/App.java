package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage scene) throws Exception {
        scene.setTitle("Calculator");
        scene.setWidth(640);
        scene.setHeight(480);
        scene.show();
    }

    public static void main(String[] args) {
        launch();
    }

}