package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Button btnHello;


    public void btnHelloClicked() {
        System.out.println("Button fired");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnHello.setOnAction(actionEvent -> btnHelloClicked());
    }
}