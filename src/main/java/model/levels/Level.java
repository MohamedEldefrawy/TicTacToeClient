package model.levels;


import javafx.scene.control.Button;

public interface Level {
    
    public Move findMove(Button[][] board, String c);
   
}
