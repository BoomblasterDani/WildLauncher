package com.example.wildlauncher;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.example.wildlauncher.Database.addApplicationToTable;

public class AddApplicationWindow extends Stage {
    AddApplicationWindow() {
        buildApplicationWindow();
    }

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
        TextField nameTextField = new TextField();
        TextField pathTextField = new TextField();
        TextField scriptPathTextField = new TextField();
        Button submit = new Button("Save");

        form.add(nameLabel, 0,0);
        form.add(nameTextField, 1,0);
        form.add(pathLabel, 0,1);
        form.add(pathTextField, 1,1);
        form.add(scriptPathLabel, 0,2);
        form.add(scriptPathTextField, 1,2);
        form.add(submit, 1, 3);




        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    addApplicationToTable(
                            nameTextField.getText(),
                            pathTextField.getText(),
                            scriptPathTextField.getText()
                    );
                    //AppletContainer.reloadAppletContainer(Database.getAcitivitiesFromTable());
                    Stage stage = new Stage();
                    HelloApplication helloApplication = new HelloApplication();
                    helloApplication.start(stage);
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
