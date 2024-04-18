package com.example.wildlauncher;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class Applet extends GridPane {

    public Applet(Application application) {
        this.application = application;
        buildApplet();
    }

    Application application;

    public void buildApplet() {
        Rectangle rect = new Rectangle();
        rect.setArcHeight(30);
        rect.setHeight(200);
        rect.setWidth(200);

        this.setClip(rect);
        if (application.running) {
            this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        this.setVisible(true);

        Text appletName = new Text(application.name);
        Button editButton = new Button("edit");
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button filesButton = new Button("Files");

        this.add(appletName, 0, 0);
        this.add(editButton, 2, 0);
        this.add(startButton, 0, 1);
        this.add(stopButton, 1, 1);
        this.add(filesButton, 2, 1);

        //this.getChildren().addAll(appletName, startButton, stopButton, filesButton);
        filesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Runtime. getRuntime(). exec("explorer.exe /select," + "D:\\downloads");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    //public static List<Applet>

}
