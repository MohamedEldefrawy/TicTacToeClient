package controllers;

import com.client.client.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Dtos.userDtos.LoginUserDto;
import utilities.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
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
    private Singleton singleton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        singleton = Singleton.getInstance();
        singleton.setConnectionHandler();


        btnSignIn.setOnAction(actionEvent -> btnSignInClicked(actionEvent));
        btnSignUp.setOnAction(actionEvent -> btnSignUpClicked(actionEvent));
    }

    public void btnSignInClicked(ActionEvent e) {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserName(userTextField.getText());
        loginUserDto.setPassword(passTextField.getText());

        checkLabel.setTextFill(Color.RED);
        if (loginUserDto.getUserName().isEmpty()) {
            checkLabel.setText("username is left empty");
            checkLabel.setVisible(true);
        } else if (loginUserDto.getPassword().isEmpty()) {
            checkLabel.setText("password is left empty");
            checkLabel.setVisible(true);
        }

        singleton.getConnectionHandler().sendLoginRequest(loginUserDto);

        while (singleton.getLoginStatus() == null) {
        }
        if (singleton.getLoginStatus()) {
            HelloApplication obj = new HelloApplication();
            try {
                obj.switchToGameFirstScene(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
