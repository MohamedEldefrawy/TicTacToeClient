package services;

import model.Dtos.gameDtos.GameInvitationDto;
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
}
