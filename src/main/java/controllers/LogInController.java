package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
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

    public Button btnSignIn;
    public Button btnSignUp;
    public TextField userTextField;
    public PasswordField passTextField;
    public Label checkLabel;
    public JFXButton btnhome;
    private Singleton singleton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        singleton = Singleton.getInstance();
        singleton.setConnectionHandler();

        btnhome.setOnAction(actionEvent -> backBtnOnClick(actionEvent));
        btnSignIn.setOnAction(this::btnSignInClicked);
        btnSignUp.setOnAction(this::btnSignUpClicked);
    }

    public void backBtnOnClick(ActionEvent e) {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToMainMenu();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void btnSignInClicked(ActionEvent e) {
        if (singleton.getConnectionHandler() != null)
            System.out.println("Singleton status from login" + singleton.getConnectionHandler());
        else
            System.out.println("connectionHandler is null");

        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserName(userTextField.getText());
        loginUserDto.setPassword(passTextField.getText());

        checkLabel.setTextFill(Color.RED);
        if (userTextField.getText().isEmpty()) {
            checkLabel.setText("username is left empty");
            checkLabel.setVisible(true);
        } else if (passTextField.getText().isEmpty()) {
            checkLabel.setText("password is left empty");
            checkLabel.setVisible(true);
        }

        singleton.getConnectionHandler().sendLoginRequest(loginUserDto);

        while (singleton.getCurrentUserDto() == null) {
            // Show Spinner
            System.out.println("Stuck!!!!!!");
        }

        if (singleton.getCurrentUserDto().isLoggedIn()) {
            HelloApplication obj = new HelloApplication();
            try {
                singleton.getCurrentUserDto().setLoggedIn(true);
                System.out.println("current user " + singleton.getCurrentUserDto().getUserName());
                obj.switchToOnlineMenuScene();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Wrong username or password");
        }
    }

    public void btnSignUpClicked(ActionEvent e) {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToSignUpScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
