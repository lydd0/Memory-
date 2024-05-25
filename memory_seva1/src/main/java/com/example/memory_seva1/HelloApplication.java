package com.example.memory_seva1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 778, 550);
            stage.setTitle("Memory");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}