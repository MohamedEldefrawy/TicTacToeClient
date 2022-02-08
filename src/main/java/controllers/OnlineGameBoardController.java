package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import controllers.threads.PlayerMoveListener;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Dtos.gameDtos.FinishGameDto;
import model.Dtos.gameDtos.PlayerMoveDto;
import model.Dtos.gameDtos.SaveGameDto;
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
    private boolean isMyTurn;
    private boolean isOpponentTurn;
    private boolean gameState;
    private PlayerMoveDto playerMoveDto;
    private String mySign;
    private String opponentSign;
    private FinishGameDto finishGameDto;
    private Thread checkThread;


    private void checkPlayerTurn(Button boardButton) {
        boardButton.setOnAction(actionEvent -> {
            if (isMyTurn && gameState) {
                if (boardButton.getText().isEmpty()) {
                    playerMoveDto = new PlayerMoveDto();
                    boardButton.setText(mySign);
                    String position = boardButton.getId().split("n")[1];
                    playerMoveDto.setPosition(position);
                    playerMoveDto.setPlayerName(player1.getText());
                    playerMoveDto.setSign(mySign);
                    playerMoveDto.setGameState(gameState);
                    playerMoveDto.setGameId(singleton.getCreatedGameDto().getGameId());
                    singleton.getConnectionHandler().sendPlayerMove(playerMoveDto);
                    isMyTurn = false;
                    isOpponentTurn = true;
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
            });
            btnPressed.fire();
            isOpponentTurn = false;
            isMyTurn = true;
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        checkThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    check();
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        checkThread.start();

        singleton = Singleton.getInstance();
        gameState = true;
        PlayerMoveListener playerMoveListener = new PlayerMoveListener();
        playerMoveListener.startThread();
        btnRecord.setOnAction(actionEvent -> {
            SaveGameDto saveGameDto = new SaveGameDto();
            saveGameDto.setUsername(singleton.getCurrentUser());
            saveGameDto.setGameId(singleton.getCreatedGameDto().getGameId());
            singleton.getConnectionHandler().sendSaveGame(saveGameDto);

        });
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
            button.setDisable(true);
        }
    }
    public void enableAllButtons() {
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }

    public void finish() {
        disableAllButtons();
    }

    public void check() {
        //check for playerWins
        if (btn1.getText().equals(mySign) && btn2.getText().equals(mySign) && btn3.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn4.getText().equals(mySign) && btn5.getText().equals(mySign) && btn6.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn7.getText().equals(mySign) && btn8.getText().equals(mySign) && btn9.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn1.getText().equals(mySign) && btn4.getText().equals(mySign) && btn7.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn2.getText().equals(mySign) && btn5.getText().equals(mySign) && btn8.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn3.getText().equals(mySign) && btn6.getText().equals(mySign) && btn9.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn1.getText().equals(mySign) && btn5.getText().equals(mySign) && btn9.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }
        if (btn3.getText().equals(mySign) && btn5.getText().equals(mySign) && btn7.getText().equals(mySign)) {
            playerWins();
            finish();
            sendWinnerRequest(singleton.getCurrentUser());
        }

        //check for opponentWins
        if (btn1.getText().equals(opponentSign) && btn2.getText().equals(opponentSign) && btn3.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());

        }
        if (btn4.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn6.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        if (btn7.getText().equals(opponentSign) && btn8.getText().equals(opponentSign) && btn9.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        if (btn1.getText().equals(opponentSign) && btn4.getText().equals(opponentSign) && btn7.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        if (btn2.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn8.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        if (btn3.getText().equals(opponentSign) && btn6.getText().equals(opponentSign) && btn9.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        if (btn1.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn9.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        if (btn3.getText().equals(opponentSign) && btn5.getText().equals(opponentSign) && btn7.getText().equals(opponentSign)) {
            opponentWins();
            finish();
            sendWinnerRequest(player2.getText());
        }
        //check for draw
        if (!btn1.getText().equals("") && !btn2.getText().equals("") && !btn3.getText().equals("") && !btn4.getText().equals("")
                && !btn5.getText().equals("") && !btn6.getText().equals("") && !btn7.getText().equals("")
                && !btn8.getText().equals("") && !btn9.getText().equals("")) {
            playersDraw();
            sendWinnerRequest("draw");
        }
    }

    private void sendWinnerRequest(String playerName) {
        finishGameDto = new FinishGameDto();
        finishGameDto.setFinished(true);
        finishGameDto.setWinnerName(playerName);
        singleton.getConnectionHandler().sendFinishGameRequest(finishGameDto);
        singleton.setGameInvitationDto(null);
        singleton.setCreatedGameDto(null);
        singleton.setGameInvitationAnswerDto(null);
        checkThread.stop();
    }

    private void playersDraw() {
        try {
            gameState = false;
            stage.switchToDrawOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerWins() {
        try {
            gameState = false;
            stage.switchToWinOnline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opponentWins() {
        try {
            stage.switchToLoseOnline();
            gameState = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

