module com.example.wildlauncher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.wildlauncher to javafx.fxml;
    exports com.example.wildlauncher;
}