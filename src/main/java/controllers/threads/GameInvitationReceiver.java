package controllers.threads;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Dtos.gameDtos.GameInvitationAnswerDto;
import model.Dtos.gameDtos.ReceiveGameInvitationDto;
import utilities.AlertsGenerator;
import utilities.Singleton;

import java.util.Optional;

public class GameInvitationReceiver {
    private final Singleton singleton;
    GameInvitationAnswerDto gameInvitationAnswerDto = new GameInvitationAnswerDto();
    private Thread gameInvitationReceiver;
    private ReceiveGameInvitationDto receiveGameInvitationDto;


    public GameInvitationReceiver() {
        singleton = Singleton.getInstance();
    }

    private boolean exit = false;

    public void startThread() {
        exit = false;
        initThread();
    }

    public void stopThread() {
        exit = true;
        gameInvitationReceiver.stop();
    }

    private void initThread() {
        gameInvitationReceiver = new Thread(() -> {
            while (!exit) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (singleton.getGameInvitationDto() != null)
                    Platform.runLater(() -> {
                        Alert alert = AlertsGenerator
                                .createGameInvitationDialog(singleton.getGameInvitationDto().getOpponentName());
                        receiveGameInvitationDto = singleton.getGameInvitationDto();
                        singleton.setGameInvitationDto(null);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                            gameInvitationAnswerDto.setAnswer(true);
                            singleton.getConnectionHandler().sendGameInvitationAnswer(gameInvitationAnswerDto);
//                            stopThread();
                            exit = true;
                        } else {
                            gameInvitationAnswerDto.setAnswer(false);
//                            singleton.setGameInvitationDto(receiveGameInvitationDto);
                            singleton.getConnectionHandler().sendGameInvitationAnswer(gameInvitationAnswerDto);
                        }
                    });
            }
        });
        gameInvitationReceiver.start();
    }


}


