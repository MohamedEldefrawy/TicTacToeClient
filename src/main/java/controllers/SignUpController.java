package controllers;

import com.client.client.HelloApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public Label checkLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Singleton singleton = Singleton.getInstance();
        singleton.setConnectionHandler();

        // create register Dto
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUseName(txtUserName.getText());
        registerUserDto.setPassword(txtPassword.getText());
        registerUserDto.setConfirmPassword(txtConfirmPassword.getText());

        btnSignup.setOnAction(event -> {
            if (txtUserName.getText().isEmpty()) {
                checkLabel.setText("Username is left empty");
                checkLabel.setVisible(true);
            } else if (txtPassword.getText().isEmpty()) {
                checkLabel.setText("Password is left empty");
                checkLabel.setVisible(true);
            } else if (txtConfirmPassword.getText().isEmpty()) {
                checkLabel.setText("Password confirmation is left empty");
                checkLabel.setVisible(true);
            }

            if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
                checkLabel.setText("Password confirmation must match password");
                checkLabel.setVisible(true);
            }

            while (singleton.getCreateUserResponse() == null) {
            }

            if (singleton.getCreateUserResponse()) {
                HelloApplication obj = new HelloApplication();
                try {
                    obj.switchToGameFirstScene(event);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Faild to create new user");
                singleton.setCreateUserResponse(null);
            }


        });


    }
}
