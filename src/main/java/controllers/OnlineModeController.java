package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Dtos.userDtos.UserDto;
import services.UserService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OnlineModeController implements Initializable {
    public ListView<String> lstOnlinePlayers;

    ObservableList<UserDto> userDtoList = FXCollections.observableArrayList();;

    //List<UserDto> userDtoList = new ArrayList<>();
    List<String> userCards = new ArrayList<>();
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
        userService = new UserService();
        btnBack.setOnAction(event -> {
            System.out.println("Clicked");
        });
        /*lstOnlinePlayers.getSelectionModel().selectedItemProperty().addListener(observable -> {
            var selectedPlayer = lstOnlinePlayers.getSelectionModel().getSelectedItem();
        });*/
        userDtoList.add(new UserDto("Dafro", 10, 5, 3, true));
        userDtoList.add(new UserDto("MO", 30, 2, 10, true));
        userDtoList.add(new UserDto("Ahmed", 15, 13, 20, true));
        userDtoList.add(new UserDto("mohamed", 15, 13, 20, true));

        col_username.setCellValueFactory(new PropertyValueFactory<UserDto,String>("userName"));
        col_wins.setCellValueFactory(new PropertyValueFactory<UserDto,Integer>("wins"));
        col_losses.setCellValueFactory(new PropertyValueFactory<UserDto,Integer>("losses"));
        col_draws.setCellValueFactory(new PropertyValueFactory<UserDto,Integer>("draws"));

        Callback<TableColumn<UserDto, Void>, TableCell<UserDto, Void>> cellFactory = new Callback<TableColumn<UserDto, Void>, TableCell<UserDto, Void>>() {
            @Override
            public TableCell<UserDto, Void> call(final TableColumn<UserDto, Void> param) {
                final TableCell<UserDto, Void> cell = new TableCell<UserDto, Void>() {

                    private final Button request = new Button("Challenge");

                    {
                        request.setOnAction((ActionEvent event) -> {
                            UserDto playerData = getTableView().getItems().get(getIndex());
                            String user = playerData.getUserName();
                            System.out.println("username: " + user);
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
                return cell;
            }
        };

        col_challenge.setCellFactory(cellFactory);
        users_table.getColumns().add(col_challenge);
        users_table.setItems(userDtoList);

        /*for (UserDto userDto : userDtoList
        ) {
            userCards.add(userService.createPlayerIdCard(userDto));
        }
        lstOnlinePlayers.getItems().addAll(userCards);*/

    }
}
