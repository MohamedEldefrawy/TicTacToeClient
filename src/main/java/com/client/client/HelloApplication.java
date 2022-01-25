package com.client.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLoginScene(ActionEvent e) throws IOException{
        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/sign_in.fxml"));
        Scene loginScene = new Scene(loginFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        st.setScene(loginScene);
    }
    public void switchToGameFirstScene(ActionEvent e) throws  IOException{
        FXMLLoader gameFirstFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/gameFirstScene.fxml"));
        Scene gameFirstScene = new Scene(gameFirstFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        st.setScene(gameFirstScene);
    }

    public void switchToSignUpScene(ActionEvent e) throws IOException {
        FXMLLoader signUpFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/signUp.fxml"));
        Scene signUpScene = new Scene(signUpFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        st.setScene(signUpScene);
    }

    public void switchToOnlineMenuScene(ActionEvent e) throws IOException {
        FXMLLoader onlineMenuFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/OnlinePlayingMenu.fxml"));
        Scene onlineMenuScene = new Scene(onlineMenuFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        st.setScene(onlineMenuScene);
    }


    public static void main(String[] args) {
        launch();
    }
}