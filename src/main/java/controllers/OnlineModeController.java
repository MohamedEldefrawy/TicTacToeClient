package controllers;

import com.jfoenix.controls.JFXButton;
import controllers.threads.GameInvitationReceiver;
import controllers.threads.GameStartThread;
import controllers.threads.TableRefresher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Dtos.gameDtos.GameInvitationDto;
import model.Dtos.userDtos.UserDto;
import services.UserService;
import utilities.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class OnlineModeController implements Initializable {
    public ListView<String> lstOnlinePlayers;
    public Text txtDraws;
    public JFXButton btnLoad;
    public Text txtUserName;
    public Text txtWins;
    public Text txtLosses;


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
        TableRefresher tableRefresher = new TableRefresher();

//        UserDto currentUser = null;

//        while (singleton.getOnlineUsers() == null) {
//
//        }
//
//        currentUser = singleton.getOnlineUsers().stream().
//                filter(userDto -> userDto.getUserName().equals(singleton.getCurrentUser())).findFirst().get();
////
//        txtUserName.setText(currentUser.getUserName());
//        txtWins.setText(String.valueOf(currentUser.getWins()));
//        txtLosses.setText(String.valueOf(currentUser.getLosses()));
//        txtDraws.setText(String.valueOf(currentUser.getDraws()));

        btnBack.setOnAction(event -> System.out.println("Clicked"));

        tableRefresher.setUserDtoList(userDtoList);
        tableRefresher.setUsers_table(users_table);

        Thread tableRefreshThread = tableRefresher.getTableRefreshThread();

        col_username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        col_wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        col_losses.setCellValueFactory(new PropertyValueFactory<>("losses"));
        col_draws.setCellValueFactory(new PropertyValueFactory<>("draws"));

        GameInvitationReceiver gameInvitationReceiver = new GameInvitationReceiver();
            gameInvitationReceiver.startThread();

        GameStartThread gameStartThread = new GameStartThread();
        gameStartThread.startThread();


        Callback<TableColumn<UserDto, Void>, TableCell<UserDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<UserDto, Void> call(final TableColumn<UserDto, Void> param) {
                return new TableCell<>() {

                    private final Button request = new Button("Challenge");

                    {
                        request.setOnAction((ActionEvent event) -> {
                            String user = singleton.getCurrentUser();
                            String opponentName = getTableView().getItems().get(getIndex()).getUserName();
                            GameInvitationDto gameInvitationDto = new GameInvitationDto();
                            gameInvitationDto.setOpponentUserName(opponentName);
                            gameInvitationDto.setUserName(user);
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
