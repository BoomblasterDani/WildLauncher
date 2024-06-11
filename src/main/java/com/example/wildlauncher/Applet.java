package com.example.wildlauncher;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.*;

public class Applet extends Pane {

    public Applet(Application application, AppletContainer appletContainer) {
        this.application = application;
        this.appletContainer = appletContainer;
        buildApplet();
    }

    Application application;
    AppletContainer appletContainer;

    public void buildApplet() {
        //Container for applet (rounded corners)

        Rectangle background = new Rectangle();


        background.setHeight(130);
        background.setWidth(200);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setFill(Color.LIGHTGREY);

        //Applet Container
        VBox vBoxContainer = new VBox();

        //BUTTONS
        Button editButton = new Button();
        editButton.setGraphic(Icon.EDIT.getIcon(15,15));

        Button startButton = new Button("");
        startButton.setGraphic(Icon.PLAY.getIcon(15,15));

        Button stopButton = new Button("");
        stopButton.setGraphic(Icon.STOP.getIcon(15,15));
        stopButton.setBackground(null);

        Button filesButton = new Button("Files");
        filesButton.setGraphic(Icon.FOLDER.getIcon(15,15));


        //Namebar



        RecktanglePane nameBar = new RecktanglePane(Color.PALEVIOLETRED, 30, 200, 15, 15);
        HBox namebarHBox = new HBox();
        Text appletName = new Text(application.name);
        Region spacing = new Region();
        namebarHBox.setPadding(new Insets(4, 10, 0, 10));
        namebarHBox.getChildren().addAll(appletName, spacing, editButton);
        HBox.setHgrow(spacing, Priority.ALWAYS);


        nameBar.getChildren().add(namebarHBox);
        nameBar.setVisible(true);






        this.setVisible(true);


        // Table for applet
        GridPane appletUiTable = new GridPane();
        appletUiTable.setPadding(new Insets(10));
        appletUiTable.setVgap(5);
        appletUiTable.setHgap(5);







        //appletUiTable.add(appletName, 0, 0);
        //appletUiTable.add(editButton, 2, 0);
        appletUiTable.add(startButton, 0, 1);
        appletUiTable.add(stopButton, 1, 1);
        appletUiTable.add(filesButton, 2, 1);


        vBoxContainer.getChildren().addAll(nameBar, appletUiTable);
        this.getChildren().addAll(background, vBoxContainer);






        // BUTTON EVENTLISTENERS
        filesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Runtime. getRuntime(). exec("explorer.exe /select," + application.path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(application.name);
                EditApplicationWindow editApplicationWindow = new EditApplicationWindow(application, appletContainer);
            }
        });


        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Process process = null;
                Task<Integer> scriptTask = new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        int id = 1;
                        Process process = null;
                        PrintWriter writer = null;

                        try {
                            ProcessBuilder processBuilder = new ProcessBuilder();
                            processBuilder.directory(new File(application.path));
                            processBuilder.command("./start.sh");


                            process = processBuilder.start();
                            writer = new PrintWriter(process.getOutputStream());


                            id = process.waitFor();

                        } catch (InterruptedException e) {

                            writer.write("stop");
                            writer.write("\n");
                            writer.flush();
                            System.out.println("teest");


                        }
                        return id;
                    }
                };

                new Thread(scriptTask).start();
                scriptTask.setOnRunning(e -> {
                    background.setFill(Color.LIGHTBLUE);
                });
                scriptTask.setOnSucceeded(e -> {
                    background.setFill(Color.LIGHTGREY);
                });
                scriptTask.setOnFailed(e -> {
                    background.setFill(Color.LIGHTGREY);
                });
                scriptTask.setOnCancelled(e -> {
                    background.setFill(Color.LIGHTGREY);
                });

                stopButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        scriptTask.cancel();
                    }
                });
            }
        });










    }

    // SERVICES
    private void runScriptTask() {
        Task<Integer> scriptTask = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                int id = 1;
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder();
                    processBuilder.command(application.scriptPath);
                    Process process = processBuilder.start();
                    id = process.waitFor();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return id;
            }
        };
    }

    //public static List<Applet>

}
