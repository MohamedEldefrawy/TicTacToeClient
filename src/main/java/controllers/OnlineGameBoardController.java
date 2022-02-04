package controllers;

import com.client.client.HelloApplication;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Dtos.gameDtos.CreatedGameDto;
import model.Dtos.gameDtos.PlayerMoveDto;
import utilities.Singleton;

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
    Singleton singleton = Singleton.getInstance();
    Random random = new Random();
    boolean player1Turn = true;
    HelloApplication stage = new HelloApplication();
    List<Button> buttons = new ArrayList<>();
    private PlayerMoveDto playerMoveDto;


    private void checkPlayerTurn(Button boardButton) {

        if (boardButton != null)
            boardButton.setOnAction(actionEvent -> {
                if (player1Turn) {
                    if (boardButton.getText().isEmpty()) {
                        boardButton.setText("X");
                        String position = boardButton.getId().split("n")[1];
                        playerMoveDto.setPosition(position);
                        singleton.getConnectionHandler().sendPlayerMove(playerMoveDto);
                        player1Turn = false;
                        check();
                    }
//                } else {
//                    if (boardButton.getText().isEmpty()) {
//                        boardButton.setText("O");
//                        player1Turn = true;
//                        check();
//                    }
                }
            });
    }

    private void disableButtons(Button button) {
        button.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);

        symbol1.setText("X");
        symbol2.setText("O");

        CreatedGameDto createdGameDto = singleton.getCreatedGameDto();
        player1.setText(createdGameDto.getPlayerX());
        player2.setText(createdGameDto.getPlayerO());

        playerMoveDto = new PlayerMoveDto();
        playerMoveDto.setPlayerName(singleton.getCurrentUser());

        for (Button button : buttons) {
            checkPlayerTurn(button);
        }

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

    public void firstTurn() {
        player1Turn = random.nextInt(2) == 0;
    }


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

      /*  HelloApplication obj = new HelloApplication();
        try{
        obj.switchToWin();
    }
            catch (IOException ex){ex.printStackTrace();}*/
    }

    public void oWins(Button a, Button b, Button c) {

      /*  HelloApplication obj = new HelloApplication();
        try{
        obj.switchToWin();
    }
            catch (IOException ex){ex.printStackTrace();}*/
    }
}
