package controllers;

import com.client.client.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DifficultyController implements Initializable {
    public Button mediumBtn;
    public Button hardBtn;
    public Button btnEasy;
    public Button backBtn1;

    public void hardBtnOnClick(ActionEvent e)
    {
        HelloApplication obj=new HelloApplication();
        try {
            obj.switchToGameBoard(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void mediumBtnOnClick(ActionEvent e)
    {
        HelloApplication obj=new HelloApplication();
        try {
            obj.switchToGameBoard(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void btnEasyOnClick(ActionEvent e)
    {
        HelloApplication obj=new HelloApplication();
        try {
            obj.switchToGameBoard(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void backBtnOnClick(ActionEvent e)
    {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToMainMenu(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEasy.setOnAction(actionEvent -> btnEasyOnClick(actionEvent));
        mediumBtn.setOnAction(actionEvent -> mediumBtnOnClick(actionEvent));
        hardBtn.setOnAction(actionEvent -> hardBtnOnClick(actionEvent));
        backBtn1.setOnAction(actionEvent -> backBtnOnClick(actionEvent));
    }
}
