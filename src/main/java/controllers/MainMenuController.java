package controllers;

import com.client.client.HelloApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Dtos.userDtos.LogoutUserDto;
import utilities.AlertsGenerator;
import utilities.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable
{
    public Button btnSinglePlayer;
    public Button btnMultiPlayer;
    public Button btnLoadGame;
    public Button btnExit;
    private Singleton singleton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        singleton = Singleton.getInstance();

        btnSinglePlayer.setOnAction(actionEvent -> {
            singleton.setConnectionHandler();
            if (singleton.getServerStatus()) {
                HelloApplication obj = new HelloApplication();
                try {
                    obj.switchToLoginScene(actionEvent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                AlertsGenerator.createWarningDialog().show();
            }
        });

        btnMultiPlayer.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try {
                obj.switchToDifficulty(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        HelloApplication.getStage().setOnCloseRequest(event -> {
            Alert alertDialog;
            alertDialog = AlertsGenerator.createConfirmationDialog();
            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                LogoutUserDto logoutUserDto = new LogoutUserDto();
                logoutUserDto.setStatus(false);
                logoutUserDto.setUserName(singleton.getCurrentUser());

                if (singleton.getCurrentUser() != null) {
                    singleton.getConnectionHandler().sendLogoutRequest(logoutUserDto);
                    singleton.getConnectionHandler().closeConnection();
                }

                System.exit(0);
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
        });

        /*btnLoadGame.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try{
                obj.switchToOnlineMenuScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnExit.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try{
                obj.switchToOnlineScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

}
