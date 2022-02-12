package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoseController implements Initializable {
    public JFXButton btnHome;
    public JFXButton btnReplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnHome.setOnAction(actionEvent -> btnHomeOnClick(actionEvent));
        btnReplay.setOnAction(actionEvent -> btnReplayOnClick(actionEvent));
    }
    public void btnHomeOnClick(ActionEvent e)
    {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToMainMenu();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void btnReplayOnClick(ActionEvent e)
    {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToDifficulty();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
