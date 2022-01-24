module com.client.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.client.client to javafx.fxml;
    exports com.client.client;
    exports controllers;
    opens controllers to javafx.fxml;
}