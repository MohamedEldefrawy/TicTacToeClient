package utilities;

import connections.ConnectionHandler;
import model.Dtos.gameDtos.CreatedGameDto;
import model.Dtos.gameDtos.GameInvitationAnswerDto;
import model.Dtos.gameDtos.ReceiveGameInvitationDto;
import model.Dtos.userDtos.UserDto;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton singleton = null;

    private ConnectionHandler connectionHandler;
    private Boolean loginStatus = null;
    private boolean createUserResponse;
    private boolean serverStatus;
    private String currentUser;
    private List<UserDto> onlineUsers = new ArrayList<>();
    private String senderName;
    private ReceiveGameInvitationDto gameInvitationDto;
    private GameInvitationAnswerDto gameInvitationAnswerDto;
    private CreatedGameDto createdGameDto;

    public CreatedGameDto getCreatedGameDto() {
        return createdGameDto;
    }

    public void setCreatedGameDto(CreatedGameDto createdGameDto) {
        this.createdGameDto = createdGameDto;
    }

    public GameInvitationAnswerDto getGameInvitationAnswerDto() {
        return gameInvitationAnswerDto;
    }

    public void setGameInvitationAnswerDto(GameInvitationAnswerDto gameInvitationAnswerDto) {
        this.gameInvitationAnswerDto = gameInvitationAnswerDto;
    }


    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    public ReceiveGameInvitationDto getGameInvitationDto() {
        return gameInvitationDto;
    }

    public void setGameInvitationDto(ReceiveGameInvitationDto gameInvitationDto) {
        this.gameInvitationDto = gameInvitationDto;
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

    public void setLoginStatus(Boolean loginStatus) {
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

    public List<UserDto> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(List<UserDto> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
