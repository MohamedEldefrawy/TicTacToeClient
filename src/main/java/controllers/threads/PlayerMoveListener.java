package controllers.threads;

import utilities.Singleton;

public class PlayerMoveListener implements Runnable {
    private final Singleton singleton = Singleton.getInstance();
    private final Thread playerMoveListenerThread;

    public PlayerMoveListener() {
        playerMoveListenerThread = new Thread(this);
    }

    public void startThread() {
        playerMoveListenerThread.start();
    }

    @Override
    public void run() {
        while (singleton.getReceivePlayerMoveDto() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
