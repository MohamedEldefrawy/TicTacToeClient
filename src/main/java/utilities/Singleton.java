package utilities;

import connections.ConnectionHandler;

import java.util.List;

public class Singleton {
    private static Singleton singleton = null;

    private ConnectionHandler connectionHandler;
    private boolean loginStatus;
    private boolean createUserResponse;
    private boolean serverStatus;
    private String currentUser;
    private List<Object> onlineUsers;


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

        if (connectionHandler == null)
            connectionHandler = new ConnectionHandler();
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Boolean getCreateUserResponse() {
        return createUserResponse;
    }

    public void setCreateUserResponse(boolean createUserResponse) {
        this.createUserResponse = createUserResponse;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public boolean getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
    }

    public List<Object> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(List<Object> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
}
