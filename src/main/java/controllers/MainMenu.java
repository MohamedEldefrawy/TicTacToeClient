package controllers;

import com.client.client.HelloApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenu  implements Initializable
{
    public Button btnSinglePlayer;
    public Button btnMultiPlayer;
    public Button btnLoadGame;
    public Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSinglePlayer.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try {
                obj.switchToLoginScene(actionEvent );
            }
            catch (IOException ex){ex.printStackTrace();}

        });
        btnMultiPlayer.setOnAction(actionEvent -> {
           HelloApplication obj = new HelloApplication();
           try{
               obj.switchToDifficulty(actionEvent);
           } catch (IOException e) {
               e.printStackTrace();
           }
        });

        /*btnLoadGame.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try{
                obj.switchToOnlineMenuScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnExit.setOnAction(actionEvent -> {
            HelloApplication obj = new HelloApplication();
            try{
                obj.switchToOnlineScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

}
