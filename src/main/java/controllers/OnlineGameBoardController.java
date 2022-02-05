package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import controllers.threads.PlayerMoveListener;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Dtos.gameDtos.PlayerMoveDto;
import utilities.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class OnlineGameBoardController implements Initializable {

    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public Text symbol2;
    public Text symbol1;
    public Text player1;
    public Text player2;
    public JFXButton btnSurrender;
    public JFXButton btnRecord;
    private final Random random = new Random();
    private static final List<Button> buttons = new ArrayList<>();
    private final HelloApplication stage = new HelloApplication();
    private Singleton singleton;
    private boolean isMyTurn = false;
    private boolean isOpponentTurn = false;
    private PlayerMoveDto playerMoveDto;
    private String mySign;
    private String opponentSign;

    private void checkPlayerTurn(Button boardButton) {

        boardButton.setOnAction(actionEvent -> {
            if (isMyTurn) {
                enableAllButtons();
                if (boardButton.getText().isEmpty()) {
                    playerMoveDto = new PlayerMoveDto();
                    boardButton.setText(mySign);
                    String position = boardButton.getId().split("n")[1];
                    playerMoveDto.setPosition(position);
                    playerMoveDto.setPlayerName(player1.getText());
                    playerMoveDto.setSign(mySign);
                    singleton.getConnectionHandler().sendPlayerMove(playerMoveDto);
                    isMyTurn = false;
                    isOpponentTurn = true;
                }
                check();
            } else {
                disableAllButtons();
                opponentTurn();
            }
        });
    }

    private void opponentTurn() {
        Platform.runLater(() -> {
            Button btnPressed = buttons.stream().filter(button -> button.getId().split("n")[1]
                            .equals(singleton.getReceivePlayerMoveDto().getPosition()))
                    .findFirst().get();
            btnPressed.setOnAction(event -> {
                Platform.runLater(() -> {
                    btnPressed.setText(opponentSign);
                });
            });
            btnPressed.fire();
            check();
            isOpponentTurn = false;
            isMyTurn = true;
        });
    }

    private void disableButton(Button button) {
        button.setDisable(true);
    }
    private void enableButton(Button button) {
        button.setDisable(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        singleton = Singleton.getInstance();
        PlayerMoveListener playerMoveListener = new PlayerMoveListener();
        playerMoveListener.startThread();
        if (singleton.getCreatedGameDto().getPlayerX().equals(singleton.getCurrentUser())) {
            mySign = "X";
            symbol1.setText(mySign);
            player1.setText(singleton.getCurrentUser());
            opponentSign = "O";
            symbol2.setText(opponentSign);
            player2.setText(singleton.getCreatedGameDto().getPlayerO());
            isMyTurn = true;
            isOpponentTurn = false;
        } else {
            mySign = "O";
            symbol1.setText(mySign);
            player1.setText(singleton.getCurrentUser());
            opponentSign = "X";
            symbol2.setText(opponentSign);
            player2.setText(singleton.getCreatedGameDto().getPlayerX());
            isMyTurn = false;
            isOpponentTurn = true;
        }


        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);


        for (Button button : buttons) {
            checkPlayerTurn(button);
        }
        singleton.setButtons(buttons);

        btnSurrender.setOnAction(event -> {
            System.out.println("surrender");
        });

        btnRecord.setOnAction(event -> {
            System.out.println("record");
        });

    }
    public void disableAllButtons() {
        for (Button button : buttons) {
            disableButton(button);
        }
    }
    public void enableAllButtons() {
        for (Button button : buttons) {
            enableButton(button);
        }
    }

    public void finish() {
        for (Button button : buttons) {
            disableButton(button);
        }
    }

//    public void firstTurn() {
//        xTicTurn = random.nextInt(2) == 0;
//    }


    public void check() {
        //check for playerWins
        if (btn1.getText().equals(mySign) && btn2.getText().equals(mySign) && btn3.getText().equals(mySign)) {
            playerWins();
            finish();
        }
        if (btn4.getText().equals(mySign) && btn5.getText().equals(mySign) && btn6.getText().equals(mySign)) {
            playerWins();
            finish();

        }
        if (btn7.getText().equals(mySign) && btn8.getText().equals(mySign) && btn9.getText().equals(mySign)) {
            playerWins();
            finish();

        }
        if (btn1.getText().equals(mySign) && btn4.getText().equals(mySign) && btn7.getText().equals(mySign)) {
            playerWins();
            finish();

        }
        if (btn2.getText().equals(mySign) && btn5.getText().equals(mySign) && btn8.getText().equals(mySign)) {
            playerWins();
            finish();
        }
        if (btn3.getText().equals(mySign) && btn6.getText().equals(mySign) && btn9.getText().equals(mySign)) {
            playerWins();
            finish();
        }
        if (btn1.getText().equals(mySign) && btn5.getText().equals(mySign) && btn9.getText().equals(mySign)) {
            playerWins();
            finish();
        }
        if (btn3.getText().equals(mySign) && btn5.getText().equals(mySign) && btn7.getText().equals(mySign)) {
            playerWins();
            finish();
        }

        //check for opponentWins
        if (btn1.getText().equals(opponentSign) && btn2.getText().equals(opponentSign) && btn3.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn4.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn6.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn7.getText().equals(opponentSign) && btn8.getText().equals(opponentSign) && btn9.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn1.getText().equals(opponentSign) && btn4.getText().equals(opponentSign) && btn7.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn2.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn8.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn3.getText().equals(opponentSign) && btn6.getText().equals(opponentSign) && btn9.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn1.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn9.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        if (btn3.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn7.getText().equals(opponentSign)) {
            opponentWins();
            finish();
        }
        //check for draw
        if(!btn1.getText().equals("") && !btn2.getText().equals("") && !btn3.getText().equals("") && !btn4.getText().equals("")
                && !btn5.getText().equals("") && !btn6.getText().equals("")&& !btn7.getText().equals("")
                && !btn8.getText().equals("") && !btn9.getText().equals("")){
            playersDraw();
        }
    }

    private void playersDraw() {
        try {
            stage.switchToDrawOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerWins() {
        try {
            stage.switchToWinOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opponentWins() {
        try {
            stage.switchToLoseOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

