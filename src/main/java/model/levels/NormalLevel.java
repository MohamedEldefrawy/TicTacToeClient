package model.levels;

import javafx.scene.control.Button;

public class NormalLevel implements Level {

    public NormalLevel() {
    }



    public  Move findMove(Button[][] board, String c){
         
         // Check Winning 
       Move temp = checkWinning(board, c);
        String t;
         if(c == "X") t="O";
           else t = "X";
        
         // Check Blocking
        if(temp.row == -1 ) temp = checkWinning(board, t);
        
        
        if(temp.row == -1){
            for(int i = 0 ; i < 3 &&temp.row ==-1 ; i++){
                for(int j = 0; j < 3&&temp.row ==-1 ; j++){
                    if(board[i][j].getText().isEmpty()){
                    temp.row = i;
                    temp.col = j;
                    }
                }
            }
        }        
        return temp;
    }

    private static Move checkWinning(Button[][] board, String c){
        Move temp = new Move();
        //   Check for winning Rows
        for(int i = 0 ; i < 3 ; i++)
        {
            if(board[i][0].getText().equals(c) && board[i][0].getText().equals(board[i][1].getText()) && board[i][2].getText().isEmpty()) {
                temp.row = i;
                temp.col = 2;
                break;
            }else if(board[i][1].getText().equals(c) && board[i][1].getText().equals(board[i][2].getText()) && board[i][0].getText().isEmpty()) {
                temp.row = i;
                temp.col = 0;
                break;
            }else if(board[i][2].getText().equals(c) && board[i][2].getText().equals(board[i][0].getText()) && board[i][1].getText().isEmpty()) {
                temp.row = i;
                temp.col = 1;
                break;
            }
        }
        //    Check for winning Columns
        for(int i = 0 ; i < 3 ; i++)
        {
            if(board[0][i].getText()==c  && board[0][i].getText().equals(board[1][i].getText()) && board[2][i].getText().isEmpty()) {
                temp.row = 2;
                temp.col = i;
                break;
            }else if(board[1][i].getText().equals(c)  && board[1][i].getText().equals(board[2][i].getText()) && board[0][i].getText().isEmpty()) {
                temp.row = 0;
                temp.col = i;
                break;
            }else if(board[2][i].getText().equals(c)  && board[2][i].getText().equals(board[0][i].getText()) && board[1][i].getText().isEmpty()) {
                temp.row = 1;
                temp.col = i;
                break;
            }
        }
        //   Check for winning X

        if(board[0][0].getText().equals(c) && board[0][0].getText().equals(board[1][1].getText()) && board[2][2].getText().isEmpty()){
            temp.row = 2;
            temp.col = 2;
        }else if(board[1][1].getText().equals(c) && board[1][1].getText().equals(board[2][2].getText()) && board[0][0].getText().isEmpty()){
            temp.row = 0;
            temp.col = 0;
        }else if(board[2][2].getText().equals(c) && board[2][2].getText().equals(board[0][0].getText()) && board[1][1].getText().isEmpty()){
            temp.row = 1;
            temp.col = 1;
        }else if(board[0][2].getText().equals(c) && board[0][2].getText().equals(board[1][1].getText()) && board[2][0].getText().isEmpty()){
            temp.row = 2;
            temp.col = 0;
        }else if(board[1][1].getText().equals(c) && board[1][1].getText().equals(board[2][0].getText()) && board[0][2].getText().isEmpty()){
            temp.row = 0;
            temp.col = 2;
        }else if(board[2][0].getText().equals(c) && board[2][0].getText().equals(board[0][2].getText()) && board[1][1].getText().isEmpty()){
            temp.row = 1;
            temp.col = 1;
        }

        return temp;
    }
}

