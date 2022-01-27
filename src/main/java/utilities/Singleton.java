package utilities;

import connections.ConnectionHandler;

public class Singleton {
    private static Singleton singleton = null;
    public ConnectionHandler connectionHandler;

    private Singleton() {
        connectionHandler = new ConnectionHandler();
    }

    public static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();

        return singleton;
    }
}
