package com.example.wildlauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;

public class Sidebar extends VBox {
    public Sidebar() {
        buildSidebar();
    }

    private void buildSidebar() {

        this.prefHeight(Double.MAX_VALUE);
        this.setMinWidth(60);
        this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setVisible(true);

        Button addButton = new Button("+");

        this.getChildren().addAll(addButton);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddApplicationWindow addApplicationWindow = new AddApplicationWindow();
            }
        });
    }


}
