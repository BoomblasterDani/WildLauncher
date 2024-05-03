package com.example.wildlauncher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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


    public void buildAppletContainer() {
        this.setVgap(10);
        this.setHgap(10);
        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(20, 20, 20, 20));




        ObservableList<Application> applications;
        try {
            createActivityTable();
            //addApplicationToTable("FirstDBe", "somewhere", "prob");
            applications = getAcitivitiesFromTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        this.getChildren().setAll(createAppletsList((ObservableList<Application>) applications));

    }

    private ObservableList<Applet> createAppletsList(ObservableList<Application> applications) {
        ObservableList<Applet> applets = FXCollections.observableArrayList();

        for (Application app : applications) {
            applets.add(new Applet(app));
        }
        return applets;
    }


}
