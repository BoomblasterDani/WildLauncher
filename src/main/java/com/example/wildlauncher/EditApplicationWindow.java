package com.example.wildlauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
        this.setTitle("Edit " + application.name);
        this.getIcons().add(Icon.WOLF.iconImg);
        GridPane form = buildFormUI();
        Scene scene = new Scene(form, 420, 240);
        this.setScene(scene);
        this.show();

    }

    private GridPane buildFormUI() {
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(5);
        form.setVgap(5);

        Label nameLabel = new Label("Name of App:");
        TextField nameTextField = new TextField(application.name);

        Label pathLabel = new Label("Enter path of App:");
        TextField pathTextField = new TextField(application.path);
        Button pathDirButton = new Button("");
        pathDirButton.setGraphic(Icon.LIGHTFOLDER.getIcon(15, 15));
        pathDirButton.setStyle("-fx-background-color: DARKVIOLET");

        Label scriptPathLabel = new Label("Enter path of start-script:");
        TextField scriptPathTextField = new TextField(application.scriptPath);
        //scriptPathTextField.setDisable(true);
        Button scriptPathDirButton = new Button("");
        scriptPathDirButton.setGraphic(Icon.LIGHTFOLDER.getIcon(15, 15));
        scriptPathDirButton.setStyle("-fx-background-color: DARKVIOLET");



        HBox buttonContainer = new HBox();
        Region spacing = new Region();
        HBox.setHgrow(spacing, Priority.ALWAYS);

        Button submit = new Button("Save");
        submit.setTextFill(Color.LIGHTGREY);
        submit.setStyle("-fx-background-color: DARKVIOLET");
        submit.setBorder(new Border(new BorderStroke(Color.DARKVIOLET,
                BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));

        Button delete = new Button("Delete");
        delete.setTextFill(Color.DARKVIOLET);
        delete.setBorder(new Border(new BorderStroke(Color.DARKVIOLET,
                BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));

        buttonContainer.getChildren().addAll(submit, spacing, delete);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        FileChooser fileChooser = new FileChooser();

        form.add(nameLabel, 0,0);
        form.add(nameTextField, 1,0);
        form.add(pathLabel, 0,1);
        form.add(pathTextField, 1,1);
        form.add(pathDirButton, 2, 1);
        form.add(scriptPathLabel, 0,2);
        form.add(scriptPathTextField, 1,2);
        form.add(scriptPathDirButton, 2, 2);
        form.add(buttonContainer, 1, 3);



        pathDirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File file = directoryChooser.showDialog(new Stage());
                pathTextField.setText(file.getAbsolutePath());
            }
        });

        scriptPathDirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                File file = fileChooser.showOpenDialog(new Stage());
                scriptPathTextField.setText(file.getAbsolutePath());
            }
        });

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



