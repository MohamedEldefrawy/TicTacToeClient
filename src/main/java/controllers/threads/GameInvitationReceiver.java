package controllers.threads;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Dtos.gameDtos.GameInvitationAnswerDto;
import utilities.AlertsGenerator;
import utilities.Singleton;

import java.util.Optional;

public class GameInvitationReceiver implements Runnable {
    private final Singleton singleton;
    private final Thread gameInvitationReceiver;


    public GameInvitationReceiver() {
        singleton = Singleton.getInstance();
        gameInvitationReceiver = new Thread(this);
    }

    public Thread getGameInvitationReceiver() {
        return gameInvitationReceiver;
    }

    @Override
    public void run() {
        GameInvitationAnswerDto gameInvitationAnswerDto = new GameInvitationAnswerDto();
        while (singleton.getGameInvitationDto() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Platform.runLater(() -> {
            Alert alert = AlertsGenerator
                    .createGameInvitationDialog(singleton.getGameInvitationDto().getOpponentName());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                gameInvitationAnswerDto.setAnswer(true);
                singleton.getConnectionHandler().sendGameInvitationAnswer(gameInvitationAnswerDto);
//                    singleton.setGameInvitationDto(null);
            } else {
                gameInvitationAnswerDto.setAnswer(false);
                singleton.getConnectionHandler().sendGameInvitationAnswer(gameInvitationAnswerDto);
//                    singleton.setGameInvitationDto(null);
            }
        });
    }
}
