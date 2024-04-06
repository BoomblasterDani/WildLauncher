package com.example.wildlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class MainContainer extends HBox {

    public MainContainer() {
        buildMainContainer();
    }
    private Scene buildMainContainer() {



        Sidebar sidebar = new Sidebar();
        AppletContainer appletContainer = new AppletContainer();



        this.getChildren().addAll(sidebar, appletContainer);
        Scene scene = new Scene(this, 320, 240);
        return scene;
    }
}
