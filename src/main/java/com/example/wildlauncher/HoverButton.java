package com.example.wildlauncher;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class HoverButton extends Button {
    Color backgroundColor;
    Color hoverColor;
    Color textColor = Color.BLACK;
    boolean bold = false;
    public HoverButton(String name, Color backgroundColor, Color hoverColor){
        this.setText(name);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        build();
    }

    public HoverButton(String name, Color backgroundColor, Color hoverColor, Color textColor){
        this.setText(name);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        build();
    }

    public HoverButton(String name, Color backgroundColor, Color hoverColor, Color textColor, Boolean bold){
        this.setText(name);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.bold = bold;
        build();
    }

    public void build(){
        String boldText = "";
        if (bold) {
            boldText = " -fx-font-weight:bold";
        }
        String finalBoldText = boldText;
        this.setStyle("-fx-background-color: #" + backgroundColor.toString().replace("0x", "") + ";" + finalBoldText);

        this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: #" + hoverColor.toString().replace("0x", "") + ";" + finalBoldText));
        this.setOnMouseExited(e -> this.setStyle("-fx-background-color: #" + backgroundColor.toString().replace("0x", "") + ";" + finalBoldText));
        this.setTextFill(Color.WHITE);
    }

}
