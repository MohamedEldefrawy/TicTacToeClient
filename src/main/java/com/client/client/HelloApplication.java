package com.client.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/com/client/views/sign in.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 400);
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/com/client/views/difficulty.fxml"));
        Scene scene5 = new Scene(fxmlLoader2.load(), 600, 400);


        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/login.fxml"));
        Scene loginScene = new Scene(loginFxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}