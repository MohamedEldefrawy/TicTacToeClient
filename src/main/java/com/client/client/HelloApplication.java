package com.client.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
        stage.setResizable(false);
        stage.show();
    }

    public void switchToMainMenu() throws IOException {
        start(currentStage);
    }

    public void switchToLoginScene() throws IOException {
        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/sign_in.fxml"));
        Scene loginScene = new Scene(loginFxmlLoader.load());
        currentStage.setScene(loginScene);
    }

    public void switchToSignUpScene() throws IOException {
        FXMLLoader signUpFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/sign_up.fxml"));
        Scene signUpScene = new Scene(signUpFxmlLoader.load());
        currentStage.setScene(signUpScene);
    }

    public void switchToOnlineMenuScene() throws IOException {
        FXMLLoader onlineMenuFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/OnlinePlayingMenu.fxml"));
        Scene onlineMenuScene = new Scene(onlineMenuFxmlLoader.load());
        currentStage.setScene(onlineMenuScene);
    }

    public void switchToDifficulty() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/difficulty.fxml"));
        Scene difficultyScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(difficultyScene);
    }

    public void switchToEasyGameBoard() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/easyGameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToMediumGameBoard() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/mediumGameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToRecordScene() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/recordScene.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToOnlinePVP() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/OnlinePlayingMenu.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToHardGameBoard() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/hardGameboard.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToWinOffline() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/winOffline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToLoseOffline() throws IOException {

        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/loseOffline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToDrawOffline() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/drawOffline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToLoseOnline() throws IOException {

        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/lose alert.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToWinOnline() throws IOException {

        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/WIN ALERT.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToDrawOnline() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/drawOnline.fxml"));
        Scene gameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(gameBoardScene);
    }

    public void switchToOnlineGameBoard() throws IOException {
        FXMLLoader difficultFxmlLoader = new FXMLLoader(getClass().getResource("/com/client/views/onlinegameboard.fxml"));
        Scene onlineGameBoardScene = new Scene(difficultFxmlLoader.load());
        currentStage.setScene(onlineGameBoardScene);
    }


    public static void main(String[] args) {
        launch();
    }


}