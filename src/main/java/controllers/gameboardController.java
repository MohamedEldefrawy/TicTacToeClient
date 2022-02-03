package controllers;

import com.client.client.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Dtos.gameDtos.CreatedGameDto;
import utilities.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class gameboardController implements Initializable {

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
    Singleton singleton = Singleton.getInstance();
    Random random = new Random();
    boolean player1Turn = true;
    HelloApplication stage = new HelloApplication();
    //Button[] arrOfBtns ={btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surrender.setOnAction(actionEvent -> btnsurrenderOnClick(actionEvent));

        CreatedGameDto createdGameDto = singleton.getCreatedGameDto();
        symbol1.setText("X");
        symbol2.setText("O");

        player1.setText(createdGameDto.getPlayerX());
        player2.setText(createdGameDto.getPlayerY());

        btn1.setOnAction(actionEvent -> {
            if (player1Turn) {
                if (btn1.getText() == "") {
                    btn1.setText("X");
                    player1Turn = false;
                    check();
                }
            } else {
                if (btn1.getText() == "") {
                    btn1.setText("O");
                    player1Turn = true;
                    check();
                }
            }
        });
        btn2.setOnAction(actionEvent -> {
            if(player1Turn)
            {
                if(btn2.getText()=="")
                {
                    btn2.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn2.getText()=="")
                {
                    btn2.setText("O");
                    player1Turn=true;
                       check();
                }}
        });
        btn3.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn3.getText()=="")
                {
                    btn3.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn3.getText()=="")
                {
                    btn3.setText("O");
                    player1Turn=true;
                       check();
                }}
        });
        btn4.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn4.getText()=="")
                {
                    btn4.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn4.getText()=="")
                {
                    btn4.setText("O");
                    player1Turn=true;
                      check();
                }}
        });
        btn5.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn5.getText()=="")
                {
                    btn5.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn5.getText()=="")
                {
                    btn5.setText("O");
                    player1Turn=true;
                       check();
                }}
        });
        btn6.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn6.getText()=="")
                {
                    btn6.setText("X");
                 //   btn6.setBackground(Color.Yellow);
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn6.getText()=="")
                {
                    btn6.setText("O");
                    player1Turn=true;
                       check();
                }}
        });
        btn7.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn7.getText()=="")
                {
                    btn7.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn7.getText()=="")
                {
                    btn7.setText("O");
                    player1Turn=true;
                       check();
                }}
        });
        btn8.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn8.getText()=="")
                {
                    btn8.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn8.getText()=="")
                {
                    btn8.setText("O");
                    player1Turn=true;
                      check();
                }}
        });
        btn9.setOnAction(actionEvent ->{
            if(player1Turn)
            {
                if(btn9.getText()=="")
                {
                    btn9.setText("X");
                    player1Turn=false;
                    check();
                }
            }
            else
            {
                if(btn9.getText()=="")
                {
                    btn9.setText("O");
                    player1Turn=true;
                      check();
                }}
        });
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
    public void btnsurrenderOnClick(ActionEvent e)
    {
        try {
            stage.switchToLoseOffline();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void firstTurn()
    {
        player1Turn = random.nextInt(2) == 0;
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

      /*  HelloApplication obj = new HelloApplication();
        try{
        obj.switchToWin();
    }
            catch (IOException ex){ex.printStackTrace();}*/
    }
    public void oWins(Button a,Button b,Button c)
    {

      /*  HelloApplication obj = new HelloApplication();
        try{
        obj.switchToWin();
    }
            catch (IOException ex){ex.printStackTrace();}*/
    }
}
