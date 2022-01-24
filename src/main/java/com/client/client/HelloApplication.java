package com.client.client;

import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/hello-view.fxml"));
       // FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
       // Scene loginScene = new Scene(loginFxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }
    public void switchToLoginScene(ActionEvent e) throws IOException{
        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/sign_in.fxml"));
        Scene loginScene = new Scene(loginFxmlLoader.load(), 400, 400);
        Stage st = (Stage)((Node)e.getSource()).getScene().getWindow();
        st.setScene(loginScene);
    }
    public void switchToGameFirstScene(ActionEvent e) throws  IOException{
        FXMLLoader gameFirstFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/gameFirstScene.fxml"));
        Scene gameFirstScene = new Scene(gameFirstFxmlLoader.load(), 400, 400);
        Stage st = (Stage)((Node)e.getSource()).getScene().getWindow();
        st.setScene(gameFirstScene);
    }
    public  void switchToSignUpScene(ActionEvent e) throws IOException{
        FXMLLoader signUpFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/signUp.fxml"));
        Scene signUpScene = new Scene(signUpFxmlLoader.load(), 400, 400);
        Stage st = (Stage)((Node)e.getSource()).getScene().getWindow();
        st.setScene(signUpScene );


    }




    public static void main(String[] args) {
        launch();
    }
}