package controllers.threads;

import model.Dtos.gameDtos.GameInvitationDto;
import utilities.Singleton;

public class GameInvitationReceiver implements Runnable {
    Singleton singleton = Singleton.getInstance();
    GameInvitationDto gameInvitationDto;

    @Override
    public void run() {
        while (true) {

        }
    }
}
