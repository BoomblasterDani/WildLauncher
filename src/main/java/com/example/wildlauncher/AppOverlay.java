package com.example.wildlauncher;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class AppOverlay { //Main window


    public Scene buildOverlay() {
        FlowPane flowPane = new FlowPane();

        Application app1 = new Application("Test", "explore", "cript", true);
        Applet applet1 = new Applet(app1);

        flowPane.getChildren().addAll(applet1);
        Scene scene = new Scene(flowPane, 320, 240);
        return scene;
    }
}
