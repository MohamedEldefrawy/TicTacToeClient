package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Button btnHello;
    public Label welcomeText;


    public void btnHelloClicked() {
        welcomeText.setText("Hello World !!!!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnHello.setOnAction(actionEvent -> btnHelloClicked());
    }
}