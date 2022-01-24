package controllers;

import com.client.client.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Button btnHello;
    public Label welcomeText;


    public void btnHelloClicked(ActionEvent e) {
       // welcomeText.setText("Hello World !!!!");
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToLoginScene(e);
        }catch (IOException ex){ex.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnHello.setOnAction(actionEvent -> btnHelloClicked(actionEvent));


    }
}
