package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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
    public Label txtError;
    public JFXButton btnback1;
    private Singleton singleton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        singleton = Singleton.getInstance();
        singleton.setConnectionHandler();
        btnback1.setOnAction(actionEvent -> backBtnOnClick(actionEvent));
        btnSignup.setOnAction(this::btnSignUpClicked);
    }
    public void backBtnOnClick(ActionEvent e)
    {
        HelloApplication obj = new HelloApplication();
        try {
            obj.switchToLoginScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void btnSignUpClicked(ActionEvent event) {
        {
            // create register Dto
            RegisterUserDto registerUserDto = new RegisterUserDto();
            registerUserDto.setUserName(txtUserName.getText());
            registerUserDto.setPassword(txtPassword.getText());

            // Validation
            if (txtUserName.getText().isEmpty()) {
                txtError.setText("Username is left empty");
                txtError.setVisible(true);
            } else if (txtPassword.getText().isEmpty()) {
                txtError.setText("Password is left empty");
                txtError.setVisible(true);
            } else if (txtConfirmPassword.getText().isEmpty()) {
                txtError.setText("Password confirmation is left empty");
                txtError.setVisible(true);
            }

            if (!(txtPassword.getText().equals(txtConfirmPassword.getText()))) {
                txtError.setText("Password confirmation must match password");
                txtError.setVisible(true);
            }

            singleton.getConnectionHandler().sendRegisterRequest(registerUserDto);

            while (!singleton.getCreateUserResponse()) {
                // Show Spinner
                System.out.println("Stuck!!!!");
            }


            if (singleton.getCreateUserResponse()) {
                HelloApplication obj = new HelloApplication();
                try {
                    obj.switchToLoginScene();
                    singleton.setCreateUserResponse(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Failed to create new user");
                singleton.setCreateUserResponse(false);
            }

        }
    }
}
