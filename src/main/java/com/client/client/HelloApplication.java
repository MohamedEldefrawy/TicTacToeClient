package com.client.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage currentStage;

    public static Stage getStage() {
        return currentStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenu(ActionEvent e) throws IOException {
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        start(st);
    }

    public void switchToLoginScene(ActionEvent e) throws IOException {
        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/sign_in.fxml"));
        Scene loginScene = new Scene(loginFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(loginScene);
    }

    public void switchToSignUpScene(ActionEvent e) throws IOException {
        FXMLLoader signUpFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/sign_up.fxml"));
        Scene signUpScene = new Scene(signUpFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(signUpScene);
    }

    public void switchToOnlineMenuScene(ActionEvent e) throws IOException {
        FXMLLoader onlineMenuFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/OnlinePlayingMenu.fxml"));
        Scene onlineMenuScene = new Scene(onlineMenuFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(onlineMenuScene);
    }

    public void switchToDifficulty(ActionEvent e) throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/difficulty.fxml"));
        Scene difiicultScene = new Scene(difficultFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(difiicultScene);
//        st.show();
    }

    public void switchToGameBoard(ActionEvent e) throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/gameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(gameBoardScene);
    }

    public void switchToEasyGameBoard(ActionEvent e) throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/easyGameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(gameBoardScene);
    }

    public void switchToMediumGameBoard(ActionEvent e) throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/mediumGameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(gameBoardScene);
    }

    public void switchToHardGameBoard(ActionEvent e) throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/hardGameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage = st;
        st.setScene(gameBoardScene);
    }

    public void switchToWinOffline() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/winOffline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = getStage();
        currentStage = st;
        st.setScene(gameBoardScene);
    }

    public void switchToLoseOffline() throws IOException {

        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/loseOffline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = getStage();
        currentStage = st;
        st.setScene(gameBoardScene);
    }

    public void switchToDrawOffline() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/drawOffline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        Stage st = getStage();
        currentStage = st;
        st.setScene(gameBoardScene);
    }


    public static void main(String[] args) {
        launch();
    }
}