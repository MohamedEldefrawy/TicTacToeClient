package controllers;

import com.client.client.HelloApplication;
import javafx.application.Platform;
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

import static javafx.application.Platform.exit;


public class MainMenuController implements Initializable {
    public Button btnSinglePlayer;
    public Button btnMultiPlayer;
    public Button btnLoadGame;
    public Button btnExit;
    private Singleton singleton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            btnMultiPlayer.setOnAction(actionEvent -> {

                singleton.setConnectionHandler();

                if (!singleton.getServerStatus()) {
                    singleton.getConnectionHandler().refreshConnection();
                }

                if (singleton.getServerStatus()) {
                    HelloApplication obj = new HelloApplication();
                    try {
                        obj.switchToLoginScene();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    singleton.setServerStatus(true);
                } else {
                    AlertsGenerator.createWarningDialog().show();
                    singleton.setServerStatus(false);
                }
            });
        });


        singleton = Singleton.getInstance();

        HelloApplication.getStage().setOnCloseRequest(event -> {
            event.consume();
            Alert alertDialog;
            alertDialog = AlertsGenerator.createConfirmationDialog();
            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                LogoutUserDto logoutUserDto = new LogoutUserDto();
                logoutUserDto.setStatus(false);

                if (singleton.getCurrentUserDto() != null) {
                    logoutUserDto.setUserName(singleton.getCurrentUserDto().getUserName());
                    singleton.getConnectionHandler().sendLogoutRequest(logoutUserDto);
                    singleton.getConnectionHandler().closeConnection();
                }

                System.exit(0);
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
        });

        btnSinglePlayer.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try {
                obj.switchToDifficulty();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnExit.setOnAction(actionEvent -> exit());
    }
}

   /*  btnExit.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try{
                obj.switchToOnlineScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/





