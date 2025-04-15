package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameController controller = new GameController();
        Scene scene = new Scene(controller.createContent());
        primaryStage.setScene(scene);
        primaryStage.setTitle("5x5 Tic Tac Toe");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

