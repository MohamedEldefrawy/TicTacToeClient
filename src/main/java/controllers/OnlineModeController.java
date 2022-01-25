package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.UserDto;
import services.UserService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OnlineModeController implements Initializable {
    public ListView<String> lstOnlinePlayers;

    List<UserDto> userDtoList = new ArrayList<>();
    List<String> userCards = new ArrayList<>();
    UserService userService;

    public Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UserService();
        btnBack.setOnAction(event -> {
            System.out.println("Clicked");
        });
        lstOnlinePlayers.getSelectionModel().selectedItemProperty().addListener(observable -> {
            var selectedPlayer = lstOnlinePlayers.getSelectionModel().getSelectedItem();
        });
        userDtoList.add(new UserDto("Dafro", 10, 5, 3, true));
        userDtoList.add(new UserDto("NO", 30, 2, 10, true));
        userDtoList.add(new UserDto("Ahmed", 15, 13, 20, false));

        for (UserDto userDto : userDtoList
        ) {
            userCards.add(userService.createPlayerIdCard(userDto));
        }
        lstOnlinePlayers.getItems().addAll(userCards);

    }
}
