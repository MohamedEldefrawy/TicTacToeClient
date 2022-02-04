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
                if (boardButton.getText().isEmpty()) {
                    playerMoveDto = new PlayerMoveDto();
                    boardButton.setText(mySign);
                    String position = boardButton.getId().split("n")[1];
                    playerMoveDto.setPosition(position);
                    playerMoveDto.setPlayerName(player1.getText());
                    singleton.getConnectionHandler().sendPlayerMove(playerMoveDto);
                    isMyTurn = false;
                    isOpponentTurn = true;
                    check();
                }
            } else {
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
                check();
            });
            btnPressed.fire();
            isOpponentTurn = false;
            isMyTurn = true;
        });
    }

    private void disableButtons(Button button) {
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

    public void finish() {
        for (Button button : buttons) {
            disableButtons(button);
        }
    }

//    public void firstTurn() {
//        xTicTurn = random.nextInt(2) == 0;
//    }


    public void check() {
        //check for XWins
        if (btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) {
            xWins(btn1, btn2, btn3);
            finish();
        }
        if (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) {
            xWins(btn4, btn5, btn6);
            finish();

        }
        if (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) {
            xWins(btn7, btn8, btn9);
            finish();

        }
        if (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) {
            xWins(btn1, btn4, btn7);
            finish();

        }
        if (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) {
            xWins(btn2, btn5, btn8);
            finish();
        }
        if (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) {
            xWins(btn3, btn6, btn9);
            finish();
        }
        if (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) {
            xWins(btn1, btn5, btn9);
            finish();
        }
        if (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X")) {
            xWins(btn3, btn5, btn7);
            finish();
        }

        //check for OWins
        if (btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O")) {
            oWins(btn1, btn2, btn3);
            finish();
        }
        if (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O")) {
            oWins(btn4, btn5, btn6);
            finish();
        }
        if (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O")) {
            oWins(btn7, btn8, btn9);
            finish();
        }
        if (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O")) {
            oWins(btn1, btn4, btn7);
            finish();
        }
        if (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O")) {
            oWins(btn2, btn5, btn8);
            finish();
        }
        if (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O")) {
            oWins(btn3, btn6, btn9);
            finish();
        }
        if (btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals("O")) {
            oWins(btn1, btn5, btn9);
            finish();
        }
        if (btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals("O")) {
            oWins(btn3, btn5, btn7);
            finish();
        }
    }

    public void xWins(Button a, Button b, Button c) {
        try {
            stage.switchToWinOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void oWins(Button a, Button b, Button c) {
        try {
            stage.switchToWinOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

