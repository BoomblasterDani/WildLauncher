package com.example.wildlauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.sql.SQLException;

import static com.example.wildlauncher.Database.addApplicationToTable;

public class AddApplicationWindow extends Stage {
    AppletContainer appletContainer; //needed to refresh appletContainer
    AddApplicationWindow(AppletContainer appletContainer) {
        buildApplicationWindow();
        this.appletContainer = appletContainer;
    }

    public void buildApplicationWindow() {
        this.setTitle("Add server");
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
        TextField nameTextField = new TextField();

        Label pathLabel = new Label("Enter path of App:");
        TextField pathTextField = new TextField();
        HoverButton pathDirButton = new HoverButton("", Color.DARKVIOLET, Color.DARKVIOLET.brighter());
        pathDirButton.setGraphic(Icon.LIGHTFOLDER.getIcon(15, 15));

        Label scriptPathLabel = new Label("Enter path of start-script:");
        TextField scriptPathTextField = new TextField();

        HoverButton scriptPathDirButton = new HoverButton("", Color.DARKVIOLET, Color.DARKVIOLET.brighter());
        scriptPathDirButton.setGraphic(Icon.LIGHTFOLDER.getIcon(15, 15));



//TODO set changes in EditApplicationWindow


        HoverButton submit = new HoverButton("Save", Color.DARKVIOLET, Color.DARKVIOLET.brighter(), Color.DARKVIOLET, true);
        submit.setTextFill(Color.LIGHTGREY);

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
        form.add(submit, 1, 3);



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
                    addApplicationToTable(
                            nameTextField.getText(),
                            pathTextField.getText(),
                            scriptPathTextField.getText()
                    );
                    //AppletContainer.reloadAppletContainer(Database.getAcitivitiesFromTable());
                    /*Stage stage = new Stage();
                    HelloApplication helloApplication = new HelloApplication();
                    helloApplication.start(stage);*/
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
