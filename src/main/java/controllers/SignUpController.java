package controllers;

import com.client.client.HelloApplication;
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
    private Singleton singleton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        singleton = Singleton.getInstance();
        singleton.setConnectionHandler();

        btnSignup.setOnAction(this::btnSignUpClicked);
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

            while (singleton.getCreateUserResponse() == null) {
                // Show Spinner
                System.out.println("Stuck!!!!");
            }


            if (singleton.getCreateUserResponse()) {
                HelloApplication obj = new HelloApplication();
                try {
                    obj.switchToLoginScene(event);
                    singleton.setCreateUserResponse(null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Failed to create new user");
                singleton.setCreateUserResponse(null);
            }

        }
    }
}
