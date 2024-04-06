package com.example.wildlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class AppletContainer extends FlowPane { //Main window
    AppletContainer() {
        buildAppletContainer();
    }


    private void buildAppletContainer() {

        Application app1 = new Application("Test", "explore", "cript", true);



        Application app2 = new Application("lol", "lil", "hey", false);

        //Sidebar sidebar = new Sidebar();
        /*try {
            ApplicationDatabase.addApplication(app1);
            //Applet applet1 = new Applet(app1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


        Applet applet2 = new Applet(app2);


        this.getChildren().addAll(applet2);

    }


}
