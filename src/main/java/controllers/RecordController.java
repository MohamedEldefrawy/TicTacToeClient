package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordController implements Initializable {
    public TableColumn player1Name;
    public TableColumn player2Name;
    public TableColumn winnerName;
    public TableColumn gameDate;
    public TableColumn loadGame;
    public JFXButton backBtn;
    public JFXButton homeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setOnAction(actionEvent -> btnBackOnClick(actionEvent));
        homeBtn.setOnAction(actionEvent -> btnHomeOnClick(actionEvent));
    }
    public void btnBackOnClick(ActionEvent e)
    {
        HelloApplication obj=new HelloApplication();
        try {
            obj.switchToOnlinePVP(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public void btnHomeOnClick(ActionEvent e)
    {
        HelloApplication obj=new HelloApplication();
        try {
            obj.switchToMainMenu(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
