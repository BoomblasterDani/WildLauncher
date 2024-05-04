package com.example.wildlauncher;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public enum Icon {
    PLAY ("/com/example/wildlauncher/icons/play.png"),
    STOP ("/com/example/wildlauncher/icons/stop.png"),
    FOLDER ("/com/example/wildlauncher/icons/folder.png"),
    PLUS ("/com/example/wildlauncher/icons/plus.png")
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
