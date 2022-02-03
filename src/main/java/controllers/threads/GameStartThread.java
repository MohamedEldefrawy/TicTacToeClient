package controllers.threads;

import com.client.client.HelloApplication;
import javafx.application.Platform;
import utilities.Singleton;

import java.io.IOException;

public class GameStartThread implements Runnable {
    private final Singleton singleton;
    private final Thread startGameThread;

    public GameStartThread() {
        singleton = Singleton.getInstance();
        startGameThread = new Thread(this);
    }

    public void startThread() {
        startGameThread.start();
    }


    @Override
    public void run() {
        while (singleton.getCreatedGameDto() == null) {

        }

        Platform.runLater(() -> {
            HelloApplication helloApplication = new HelloApplication();
            try {
                helloApplication.switchToGameBoard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
