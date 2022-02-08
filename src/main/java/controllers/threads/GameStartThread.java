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

    public void stopThread() {
        startGameThread.stop();
    }


    @Override
    public void run() {
        System.out.println("Game start thread is running in peace");
        while (singleton.getCreatedGameDto() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Platform.runLater(() -> {
            HelloApplication helloApplication = new HelloApplication();
            try {
                helloApplication.switchToOnlineGameBoard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
