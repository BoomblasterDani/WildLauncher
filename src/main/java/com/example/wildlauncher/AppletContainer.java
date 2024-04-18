package com.example.wildlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.wildlauncher.Database.*;

public class AppletContainer extends FlowPane { //Main window
    AppletContainer() {
        buildAppletContainer();
    }


    private void buildAppletContainer() {
        this.setVgap(10);
        this.setHgap(10);




        List<Application> applications;
        try {
            createActivityTable();
            //addApplicationToTable("FirstDBe", "somewhere", "prob");
            applications = getAcitivitiesFromTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        /*Application app1 = new Application("Test", "explore", "cript");


        Application app2 = new Application("lol", "lil", "hey");

        //Sidebar sidebar = new Sidebar();

        Applet applet1 = new Applet(app1);
        Applet applet2 = new Applet(app2);
        Applet applet3 = new Applet(applications.get(0));*/


        this.getChildren().addAll(createAppletsList(applications));

    }

    private List<Applet> createAppletsList(List<Application> applications) {
        List<Applet> applets = new ArrayList<>();

        for (Application app : applications) {
            applets.add(new Applet(app));
        }
        return applets;
    }
}
