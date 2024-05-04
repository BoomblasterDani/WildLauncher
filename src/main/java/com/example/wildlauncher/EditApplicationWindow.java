package com.example.wildlauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Stack;

import static com.example.wildlauncher.Database.*;

public class EditApplicationWindow extends Stage {


    EditApplicationWindow(Application application, AppletContainer appletContainer) {
        this.appletContainer = appletContainer;
        this.application = application;
        buildApplicationWindow();
    }

    AppletContainer appletContainer; //needed to refresh appletContainer
    Application application;

    public void buildApplicationWindow() {
        this.setTitle("test");
        GridPane form = buildFormUI();
        Scene scene = new Scene(form, 320, 240);
        this.setScene(scene);
        this.show();

    }

    private GridPane buildFormUI() {
        GridPane form = new GridPane();

        Label nameLabel = new Label("Name of App:");
        Label pathLabel = new Label("Enter path of App");
        Label scriptPathLabel = new Label("Enter path of start-script");
        TextField nameTextField = new TextField(application.name);
        TextField pathTextField = new TextField(application.path);
        TextField scriptPathTextField = new TextField(application.scriptPath);
        Button submit = new Button("Save");
        Button delete = new Button("Delete Applet");

        form.add(nameLabel, 0,0);
        form.add(nameTextField, 1,0);
        form.add(pathLabel, 0,1);
        form.add(pathTextField, 1,1);
        form.add(scriptPathLabel, 0,2);
        form.add(scriptPathTextField, 1,2);
        form.add(submit, 1, 3);
        form.add(delete, 2, 3);




        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    editApplicationInTable(
                            application.id,
                            nameTextField.getText(),
                            pathTextField.getText(),
                            scriptPathTextField.getText()
                    );
                    appletContainer.buildAppletContainer();
                    closeWindow();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    deleteApplication(application.id);
                    appletContainer.buildAppletContainer();
                    closeWindow();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        return form;
    }



    private void closeWindow() {
        this.close();
    }

}



