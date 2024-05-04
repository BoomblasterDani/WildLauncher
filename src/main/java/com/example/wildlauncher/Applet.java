package com.example.wildlauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;

public class Applet extends Pane {

    public Applet(Application application, AppletContainer appletContainer) {
        this.application = application;
        this.appletContainer = appletContainer;
        buildApplet();
    }

    Application application;
    AppletContainer appletContainer;

    public void buildApplet() {
        //Container for applet (rounded corners)

        Rectangle rect = new Rectangle();

        rect.setHeight(100);
        rect.setWidth(200);
        rect.setArcHeight(15);
        rect.setArcWidth(15);
        rect.setFill(Color.LIGHTGREY);

        //rect.setClip(rect);
        /*if (application.running) {
            rect.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }*/

        this.setVisible(true);


        // UI for applet
        GridPane appletUi = new GridPane();
        appletUi.setPadding(new Insets(10));
        appletUi.setVgap(5);
        appletUi.setHgap(5);

        Text appletName = new Text(application.name);
        Button editButton = new Button("edit");
        Button startButton = new Button("");
        startButton.setGraphic(Icon.PLAY.getIcon(15,15));

        Button stopButton = new Button("");
        stopButton.setGraphic(Icon.STOP.getIcon(15,15));
        stopButton.setBackground(null);

        Button filesButton = new Button("Files");
        filesButton.setGraphic(Icon.FOLDER.getIcon(15,15));

        appletUi.add(appletName, 0, 0);
        appletUi.add(editButton, 2, 0);
        appletUi.add(startButton, 0, 1);
        appletUi.add(stopButton, 1, 1);
        appletUi.add(filesButton, 2, 1);

        this.getChildren().addAll(rect, appletUi);
        filesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Runtime. getRuntime(). exec("explorer.exe /select," + application.path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(application.name);
                EditApplicationWindow editApplicationWindow = new EditApplicationWindow(application, appletContainer);
            }
        });

    }

    //public static List<Applet>

}
