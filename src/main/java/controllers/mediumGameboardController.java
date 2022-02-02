package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import model.levels.EasyLevel;
import model.levels.Move;
import model.levels.NormalLevel;

public class mediumGameboardController implements Initializable {
    NormalLevel Medium = new NormalLevel();
    public Button[][] board = new Button[3][3];


    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public Button surrender;
    public Button record;
    public Text symbol2;
    public Text symbol1;
    public Text player1;
    public Text player2;
    private String player = "X";
    private boolean winner = false;
    int moveNum = 0;
    Move bestMove;
    private Boolean computerWin = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board[0][0] = btn1;
        board[0][1] = btn2;
        board[0][2] = btn3;
        board[1][0] = btn4;
        board[1][1] = btn5;
        board[1][2] = btn6;
        board[2][0] = btn7;
        board[2][1] = btn8;
        board[2][2] = btn9;

        for (Button[] btns : board) {
            for (Button btn : btns) {
                btn.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                    if (!winner) {
                        btn.setText("X");
                        check();
                        btn.setMouseTransparent(true);
                        if (moveNum + 1 < 9 && winner==false) {
                            bestMove = Medium.findMove(board, "O");
                            board[bestMove.row][bestMove.col].setText("O");
                            check();
                            board[bestMove.row][bestMove.col].setMouseTransparent(true);
                        }
                        moveNum += 2;
                    }
                });
            }
        }
    }
    public void check() {
        //check for XWins
        if (btn1.getText()=="X" && btn2.getText()=="X" && btn3.getText()=="X" ) {
            xWins(btn1,btn2,btn3);
            finish();
        }
        if (btn4.getText()=="X" &&btn5.getText()=="X" &&btn6.getText()=="X" ) {
            xWins(btn4,btn5,btn6);
            finish();
        }
        if (btn7.getText()=="X" &&btn8.getText()=="X" &&btn9.getText()=="X" ) {
            xWins(btn7,btn8,btn9);
            finish();
        }
        if (btn1.getText()=="X" &&btn4.getText()=="X" &&btn7.getText()=="X" ) {
            xWins(btn1,btn4,btn7);
            finish();
        }
        if (btn2.getText()=="X" &&btn5.getText()=="X" &&btn8.getText()=="X" ) {
            xWins(btn2,btn5,btn8);
            finish();
        }
        if (btn3.getText()=="X" &&btn6.getText()=="X" &&btn9.getText()=="X" ) {
            xWins(btn3,btn6,btn9);
            finish();
        }
        if (btn1.getText()=="X" &&btn5.getText()=="X" &&btn9.getText()=="X" ) {
            xWins(btn1,btn5,btn9);
            finish();
        }
        if (btn3.getText()=="X" &&btn5.getText()=="X" &&btn7.getText()=="X" ) {
            xWins(btn3,btn5,btn7);
            finish();
        }

        //check for OWins
        if (btn1.getText()=="O" && btn2.getText()=="O" && btn3.getText()=="O" ) {
            oWins(btn1,btn2,btn3);
            finish();
        }
        if (btn4.getText()=="O" &&btn5.getText()=="O" &&btn6.getText()=="O" ) {
            oWins(btn4,btn5,btn6);
            finish();
        }
        if (btn7.getText()=="O" &&btn8.getText()=="O" &&btn9.getText()=="O" ) {
            oWins(btn7,btn8,btn9);
            finish();
        }
        if (btn1.getText()=="O" &&btn4.getText()=="O" &&btn7.getText()=="O" ) {
            oWins(btn1,btn4,btn7);
            finish();
        }
        if (btn2.getText()=="O" &&btn5.getText()=="O" &&btn8.getText()=="O" ) {
            oWins(btn2,btn5,btn8);
            finish();
        }
        if (btn3.getText()=="O" &&btn6.getText()=="O" &&btn9.getText()=="O" ) {
            oWins(btn3,btn6,btn9);
            finish();
        }
        if (btn1.getText()=="O" &&btn5.getText()=="O" &&btn9.getText()=="O" ) {
            oWins(btn1,btn5,btn9);
            finish();
        }
        if (btn3.getText()=="O" &&btn5.getText()=="O" &&btn7.getText()=="O" ) {
            oWins(btn3,btn5,btn7);
            finish();
        }
    }

    public void xWins(Button a,Button b,Button c)
    {
        winner=true;
        //a.setBackground(Color.YELLOW);
      /*  HelloApplication obj = new HelloApplication();
        try{
        obj.switchToWin();
    }
            catch (IOException ex){ex.printStackTrace();}*/
    }
    public void oWins(Button a,Button b,Button c)
    {
        computerWin=true;
      /*  HelloApplication obj = new HelloApplication();
        try{
        obj.switchToWin();
    }
            catch (IOException ex){ex.printStackTrace();}*/
    }
    public void finish() {
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        btn8.setDisable(true);
        btn9.setDisable(true);
    }
}
