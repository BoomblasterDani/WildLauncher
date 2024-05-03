package com.example.wildlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class MainContainer extends HBox {

    public MainContainer() {
        buildMainContainer();
    }
    public Scene buildMainContainer() {




        AppletContainer appletContainer = new AppletContainer();
        Sidebar sidebar = new Sidebar(appletContainer);



        this.getChildren().addAll(sidebar, appletContainer);
        Scene scene = new Scene(this, 320, 240);
        return scene;
    }


    public static void loadAppletContainer() {

    }


}
