package utilities;

import connections.ConnectionHandler;

public class Singleton {
    private static Singleton singleton = null;

    private ConnectionHandler connectionHandler;
    private Boolean loginStatus = null;
    private Boolean createUserResponse = null;


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

    public Boolean getCreateUserResponse() {
        return createUserResponse;
    }

    public void setCreateUserResponse(Boolean createUserResponse) {
        this.createUserResponse = createUserResponse;
    }
}
