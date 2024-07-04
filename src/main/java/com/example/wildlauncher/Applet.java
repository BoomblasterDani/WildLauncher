package com.example.wildlauncher;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        //LABELS

        Label portLabel = new Label("RAM:");
        //Label portNumberLabel = new Label(ReadProperties.readFromFile(new File(application.path + "\\server.properties"), "server-port=", "enable-rcon"));
        Label portNumberLabel = new Label("");
        Label cpuLabel = new Label("CPU:");

        //BUTTONS
        HoverButton editButton = new HoverButton("", Color.TRANSPARENT, Color.DARKVIOLET.brighter());
        editButton.setGraphic(Icon.LIGHTEDIT.getIcon(20,20));
        editButton.setBackground(null);
        editButton.setMaxHeight(20);
        editButton.setMinHeight(15);

        Button startButton = new Button("");
        startButton.setGraphic(Icon.PLAY.getIcon(15,15));

        Button restart = new Button("Restart");
        //restart.set(false);

        Button stopButton = new Button("");
        stopButton.setGraphic(Icon.STOP.getIcon(15,15));
        //stopButton.setBackground(null);

        Button filesButton = new Button("");
        filesButton.setGraphic(Icon.FOLDER.getIcon(15,15));


        //Namebar
        //System.out.println(Color.BROWN.toString().replace("0x", ""));



        RecktanglePane nameBar = new RecktanglePane(Color.DARKVIOLET, 30, 200, 15, 15);
        HBox namebarHBox = new HBox();
        Text appletName = new Text(application.name + ": ");
        appletName.setFill(Color.LIGHTGREY);
        appletName.setStyle("-fx-font-weight: bolder");
        Text port = new Text(ReadProperties.readFromFile(new File(application.path + "\\server.properties"), "server-port=", "simulation-distance"));
        //Text port = new Text("25565");
        port.setFill(Color.LIGHTGREY);
        Region spacing = new Region();
        namebarHBox.setPadding(new Insets(4, 5, 0, 10));
        namebarHBox.getChildren().addAll(appletName, port, spacing, editButton);
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
        appletUiTable.add(portLabel, 0, 0);
        appletUiTable.add(portNumberLabel, 1, 0);
        appletUiTable.add(cpuLabel, 0, 1);
        appletUiTable.add(startButton, 0, 2);
        //appletUiTable.add(restart, 0, 2);
        appletUiTable.add(stopButton, 1, 2);
        appletUiTable.add(filesButton, 2, 2);


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
                            System.out.println(application.scriptPath.replace("\\", "\\\\"));
                            processBuilder.command(application.scriptPath.replace("\\", "\\\\"));
                            //processBuilder.command(application.scriptPath.replaceAll("\\", "\\\\"));



                            process = processBuilder.start();

                            writer = new PrintWriter(process.getOutputStream());

                            System.out.println(process.info().totalCpuDuration())  ;

                            Runtime runtime = Runtime.getRuntime();
                            System.out.println(runtime.totalMemory());
                            System.out.println(process.info().totalCpuDuration()) ;

                            id = process.waitFor();

                        } catch (InterruptedException e) {
                            writeToConsole(writer,"stop");



                        }
                        return id;
                    }
                };

                new Thread(scriptTask).start();
                scriptTask.setOnRunning(e -> {
                    background.setFill(Color.THISTLE);
                    startButton.setVisible(false);
                    restart.setVisible(true);

                });
                scriptTask.setOnSucceeded(e -> {
                    background.setFill(Color.LIGHTGREY);
                    startButton.setVisible(true);
                    restart.setVisible(false);
                });
                scriptTask.setOnFailed(e -> {
                    background.setFill(Color.LIGHTGREY);
                    startButton.setVisible(true);
                    restart.setVisible(false);
                });
                scriptTask.setOnCancelled(e -> {
                    background.setFill(Color.LIGHTGREY);
                    startButton.setVisible(true);
                    restart.setVisible(false);
                });

                stopButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        scriptTask.cancel();
                    }
                });

                restart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stopButton.fire();
                        startButton.fire();
                    }
                });
            }
        });










    }

    private void writeToConsole(PrintWriter writer, String command) {
        writer.write(command);
        writer.write("\n");
        writer.flush();
        System.out.println("teest");
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
