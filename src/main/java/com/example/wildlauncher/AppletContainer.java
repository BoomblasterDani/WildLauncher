package com.example.wildlauncher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.sql.SQLException;

import static com.example.wildlauncher.Database.*;

public class AppletContainer extends FlowPane { //Main window
    AppletContainer() {
        buildAppletContainer();
    }


    public void buildAppletContainer() {
        this.setVgap(20);
        this.setHgap(20);
        this.setOrientation(Orientation.HORIZONTAL);
        //this.setMaxWidth(800);
        //this.setWidth(800);
        this.setPrefWidth(6000);
        this.setPadding(new Insets(20, 20, 20, 20));




        ObservableList<Application> applications;
        try {
            createActivityTable();
            //addApplicationToTable("FirstDBe", "somewhere", "prob");
            applications = getApplicationsFromTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        this.getChildren().setAll(createAppletsList((ObservableList<Application>) applications));

    }

    private ObservableList<Applet> createAppletsList(ObservableList<Application> applications) {
        ObservableList<Applet> applets = FXCollections.observableArrayList();

        for (Application app : applications) {
            applets.add(new Applet(app, this));
        }
        return applets;
    }


}
