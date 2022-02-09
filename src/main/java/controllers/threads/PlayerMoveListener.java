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
        System.out.println("player move listener thread started peacefully");

        while (singleton.getReceivePlayerMoveDto() == null) {
            System.out.println("receivePlayerMoveDto = null");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
