package services;

import model.Dtos.gameDtos.*;
import utilities.JsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public class GameService {

    public void sendGameInvitation(GameInvitationDto gameInvitationDto, DataOutputStream writer) {
        String request = JsonBuilder.sendInvitation(gameInvitationDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGameInvitationAnswer(GameInvitationAnswerDto gameInvitationAnswerDto, DataOutputStream writer) {
        String request = JsonBuilder.sendInvitationAnswer(gameInvitationAnswerDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendPlayerMove (PlayerMoveDto playerMoveDto, DataOutputStream writer) {
        String request = JsonBuilder.sendPlayerMove(playerMoveDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSaveGame(SaveGameDto saveGameDto, DataOutputStream writer) {
        String request = JsonBuilder.sendSaveGame(saveGameDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFinishGame(FinishGameDto finishGameDto, DataOutputStream writer) {
        String request = JsonBuilder.finishGameRequest(finishGameDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
