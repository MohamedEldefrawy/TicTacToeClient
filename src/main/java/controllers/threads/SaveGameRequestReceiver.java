package controllers.threads;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Dtos.gameDtos.SaveGameResponseDto;
import utilities.AlertsGenerator;
import utilities.Singleton;

import java.util.Optional;

public class SaveGameRequestReceiver {
    SaveGameResponseDto saveGameResponseDto;
    private final Singleton singleton = Singleton.getInstance();
    private boolean exit = false;


    public SaveGameRequestReceiver() {
        saveGameResponseDto = new SaveGameResponseDto();
    }

    public void startThread() {
        exit = false;
        initThread();
    }

    private void initThread() {
        Thread saveGameReceiver = new Thread(() -> {
            System.out.println("Save game request thread started peacefully");
            while (!exit) {
                if (singleton.getSaveGameRequestDto() != null) {

                    Platform.runLater(() -> {
                        Alert alert = AlertsGenerator
                                .createSaveGameInvitationDialog(singleton.getSaveGameRequestDto().getOpponentName());
                        singleton.setSaveGameRequestDto(null);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                            saveGameResponseDto.setAnswer(true);
                            singleton.getConnectionHandler().sendSaveGameResponse(saveGameResponseDto);
                            exit = true;
                        } else {
                            saveGameResponseDto.setAnswer(false);
                            singleton.getConnectionHandler().sendSaveGameResponse(saveGameResponseDto);
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
        saveGameReceiver.start();
    }

}
