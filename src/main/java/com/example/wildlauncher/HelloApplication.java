package com.example.wildlauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppOverlay overlay = new AppOverlay();


        stage.setTitle("Hello!");
        stage.setScene(overlay.buildOverlay());
        stage.show();

        //Runtime. getRuntime(). exec("explorer.exe /select," + "D:\\downloads");
    }

    public static void main(String[] args) {
        launch();
    }
}