package controllers.threads;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Dtos.gameDtos.GameInvitationAnswerDto;
import utilities.AlertsGenerator;
import utilities.Singleton;

import java.util.Optional;

public class GameInvitationReceiver {
    private final Singleton singleton;
    GameInvitationAnswerDto gameInvitationAnswerDto = new GameInvitationAnswerDto();

    public GameInvitationReceiver() {
        singleton = Singleton.getInstance();
    }

    private boolean exit = false;

    public void startThread() {
        exit = false;
        initThread();
    }

    private void initThread() {
        Thread gameInvitationReceiver = new Thread(() -> {
            while (!exit) {
                if (singleton.getGameInvitationDto() != null) {

                    Platform.runLater(() -> {
                        if (singleton.getGameInvitationDto() != null) {
                            Alert alert = AlertsGenerator
                                    .createGameInvitationDialog(singleton.getGameInvitationDto().getOpponentName());
                            singleton.setGameInvitationDto(null);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                                gameInvitationAnswerDto.setAnswer(true);
                                singleton.getConnectionHandler().sendGameInvitationAnswer(gameInvitationAnswerDto);
                                exit = true;
                            } else {
                                gameInvitationAnswerDto.setAnswer(false);
                                singleton.getConnectionHandler().sendGameInvitationAnswer(gameInvitationAnswerDto);
                            }
                        }
                    });
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameInvitationReceiver.start();
    }
}


