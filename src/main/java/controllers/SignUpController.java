package controllers;

import com.client.client.HelloApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Dtos.userDtos.RegisterUserDto;
import utilities.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    public TextField txtUserName;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;
    public Button btnSignup;
    public Hyperlink lnkHaveAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Singleton singleton = Singleton.getInstance();
        // create register Dto
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUseName(txtUserName.getText());
        registerUserDto.setPassword(txtPassword.getText());
        registerUserDto.setConfirmPassword(txtConfirmPassword.getText());
        btnSignup.setOnAction(event -> {
            if (registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
                boolean result = singleton.connectionHandler.sendRegisterRequest(registerUserDto);
                if (result) {
                    System.out.println("user has been created.");
                    HelloApplication obj = new HelloApplication();
                    try {
                        obj.switchToLoginScene(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Failed to create user");
                }
            } else {
                System.out.println("please make sure password and confirm password are the same");
            }

        });


    }
}
