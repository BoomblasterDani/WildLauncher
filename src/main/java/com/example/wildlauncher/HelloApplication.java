package com.example.wildlauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        MainContainer mainContainer = new MainContainer();

        try {
            ApplicationDatabase.createTableApplications();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        stage.setTitle("Hello!");
        stage.setScene(mainContainer.getScene());
        stage.show();

        //Runtime. getRuntime(). exec("explorer.exe /select," + "D:\\downloads");
    }

    public static void main(String[] args) {
        launch();
    }
}