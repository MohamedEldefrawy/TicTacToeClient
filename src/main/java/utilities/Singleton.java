package utilities;

import connections.ConnectionHandler;

public class Singleton {
    private static Singleton singleton = null;

    private ConnectionHandler connectionHandler;
    private Boolean loginStatus = null;


    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public void setConnectionHandler() {
        connectionHandler = new ConnectionHandler();
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
