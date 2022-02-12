package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadGameController implements Initializable {
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public Text player1;
    public Text symbol1;
    public Text player2;
    public Text symbol2;
    public JFXButton backBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setOnAction(actionEvent -> btnBackOnClick(actionEvent));
    }

    public void btnBackOnClick(ActionEvent e) {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToRecordScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
