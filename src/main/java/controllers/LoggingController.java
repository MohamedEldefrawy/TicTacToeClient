package controllers;

import com.client.client.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggingController implements Initializable {
    public void btnSignUpClicked(ActionEvent e) {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToSignUpScene(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Button btnSignIn;
    public Button btnSignUp;
    public TextField userTextField;
    public PasswordField passTextField;
    public Label checkLabel;

    String user = "ahmed";
    String psswd = "12345";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSignIn.setOnAction(actionEvent -> btnSignInClicked(actionEvent));
        btnSignUp.setOnAction(actionEvent -> btnSignUpClicked(actionEvent));
    }

    public void btnSignInClicked(ActionEvent e) {
        String userText = userTextField.getText();
        String psswdText = passTextField.getText();
        checkLabel.setTextFill(Color.RED);
        if (userText.isEmpty()) {
            checkLabel.setText("username is left empty");
            checkLabel.setVisible(true);
        } else if (psswdText.isEmpty()) {
            checkLabel.setText("password is left empty");
            checkLabel.setVisible(true);
        } else if (!userText.equals(user)) {
            checkLabel.setText("you entered wrong username");
            checkLabel.setVisible(true);
        } else if (userText.equals(user) && !psswdText.equals(psswd)) {
            checkLabel.setText("you entered wrong password");
            checkLabel.setVisible(true);
        } else {
            HelloApplication obj = new HelloApplication();
            try {
                obj.switchToGameFirstScene(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }

}
