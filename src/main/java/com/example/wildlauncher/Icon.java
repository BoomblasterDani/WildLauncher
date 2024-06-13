package com.example.wildlauncher;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public enum Icon {
    PLAY ("/com/example/wildlauncher/icons/play.png"),
    STOP ("/com/example/wildlauncher/icons/stop.png"),
    EDIT ("/com/example/wildlauncher/icons/edit.png"),
    LIGHTEDIT ("/com/example/wildlauncher/icons/edit-light.png"),
    FOLDER ("/com/example/wildlauncher/icons/folder.png"),
    LIGHTFOLDER ("/com/example/wildlauncher/icons/folder-light.png"),
    PLUS ("/com/example/wildlauncher/icons/plus.png"),
    WOLF ("/com/example/wildlauncher/icons/wolf.png")
    ;

    Image iconImg;

    Icon(String imagePath) {
        iconImg = new Image((getClass().getResourceAsStream(imagePath)), 100, 100, false, false);
    }

    public ImageView getIcon(int width, int height) {
        ImageView iconImgView = new ImageView(iconImg);
        iconImgView.setFitWidth(width);
        iconImgView.setFitHeight(height);
        return iconImgView;
    }
}
