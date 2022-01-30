module com.client.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires com.google.gson;

    opens com.client.client to javafx.fxml;
    exports com.client.client;
    exports controllers;
    exports model.Dtos.userDtos;
    opens controllers to javafx.fxml;
}