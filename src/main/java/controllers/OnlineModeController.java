package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Dtos.gameDtos.GameInvitationDto;
import model.Dtos.userDtos.UserDto;
import services.UserService;
import utilities.Singleton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OnlineModeController implements Initializable {
    public ListView<String> lstOnlinePlayers;

    ObservableList<UserDto> userDtoList = FXCollections.observableArrayList();
    UserService userService;
    public Button btnBack;
    @FXML
    public TableView<UserDto> users_table;
    @FXML
    public TableColumn<UserDto, String> col_username;
    @FXML
    public TableColumn<UserDto, Integer> col_wins;
    @FXML
    public TableColumn<UserDto, Integer> col_losses;
    @FXML
    public TableColumn<UserDto, Integer> col_draws;
    @FXML
    public TableColumn<UserDto, Void> col_challenge;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Singleton singleton = Singleton.getInstance();
        userService = new UserService();


        btnBack.setOnAction(event -> System.out.println("Clicked"));

        new Thread(() -> {
            int prevOnlinePlayers = 0;
            while (true) {
                if (singleton.getOnlineUsers().size() != prevOnlinePlayers) {
                    List<UserDto> result = singleton.getOnlineUsers().stream().filter(userDto -> !(userDto.getUserName().equals(singleton.getCurrentUser()))).toList();
                    userDtoList.clear();
                    userDtoList.addAll(result);
                    prevOnlinePlayers = singleton.getOnlineUsers().size();
                }
                users_table.refresh();
            }
        }).start();


        col_username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        col_wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        col_losses.setCellValueFactory(new PropertyValueFactory<>("losses"));
        col_draws.setCellValueFactory(new PropertyValueFactory<>("draws"));

        Callback<TableColumn<UserDto, Void>, TableCell<UserDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<UserDto, Void> call(final TableColumn<UserDto, Void> param) {
                return new TableCell<>() {

                    private final Button request = new Button("Challenge");

                    {
                        request.setOnAction((ActionEvent event) -> {
                            UserDto playerData = getTableView().getItems().get(getIndex());
                            String user = playerData.getUserName();
                            GameInvitationDto gameInvitationDto = new GameInvitationDto();
                            gameInvitationDto.setOpponentUserName(user);
                            gameInvitationDto.setUserName(singleton.getCurrentUser());
                            System.out.println("username: " + user);

                            singleton.getConnectionHandler().sendGameInvitation(gameInvitationDto);

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(request);
                        }
                    }
                };
            }
        };

        col_challenge.setCellFactory(cellFactory);
        users_table.setItems(userDtoList);
    }
}
