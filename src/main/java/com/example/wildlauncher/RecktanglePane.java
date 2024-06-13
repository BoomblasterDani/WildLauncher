package com.example.wildlauncher;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class RecktanglePane extends StackPane {

    Rectangle background = new Rectangle();




    public RecktanglePane (Color color, double height, double with, double arcHeight, double arcWidth) {
        background.setFill(color);
        background.setHeight(height);
        background.setWidth(with);
        background.setArcHeight(arcHeight);
        background.setArcWidth(arcWidth);

        this.getChildren().addAll(background);
    }

    public RecktanglePane (Color color, double height, double with) {
        background.setFill(color);
        background.setHeight(height);
        background.setWidth(with);
        this.getChildren().addAll(background);
    }

    public void setFill(Color color) {
        background.setFill(color);
    }
}
