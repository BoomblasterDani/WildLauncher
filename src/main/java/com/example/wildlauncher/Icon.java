package com.example.wildlauncher;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Icon {
    PLAY ("icons/play.png"),
    STOP (""),
    RESTART ("");

    Icon(String s) {
        ImageView imageView = new ImageView(new Image(s));
    }
}
