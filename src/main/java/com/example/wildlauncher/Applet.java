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

import java.io.IOException;

public class Applet extends Pane {

    public Applet(Application application) {
        this.application = application;
        buildApplet();
    }

    Application application;

    public void buildApplet() {
        //Container for applet (rounded corners)

        Rectangle rect = new Rectangle();

        rect.setHeight(100);
        rect.setWidth(200);
        rect.setArcHeight(15);
        rect.setArcWidth(15);
        rect.setFill(Color.GREY);

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

        Text appletName = new Text(application.name);
        Button editButton = new Button("edit");
        Button startButton = new Button("Start");
        //Image img = new Image("icons\\play.png");
        //ImageView imgV = new ImageView();
        //imgV.setImage(img);

        //startButton.setGraphic(imgV);
        Button stopButton = new Button("Stop");
        Button filesButton = new Button("Files");

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
                    Runtime. getRuntime(). exec("explorer.exe /select," + "D:\\downloads");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    //public static List<Applet>

}
