package connections;

import com.google.gson.JsonObject;
import model.Dtos.gameDtos.GameInvitationDto;
import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;
import services.GameService;
import services.UserService;
import utilities.JsonBuilder;
import utilities.Singleton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionHandler {
    private Socket socket;
    private DataInputStream reader;
    private DataOutputStream writer;
    private final UserService userService;
    private final GameService gameService;
    private final Singleton singleton = Singleton.getInstance();

    public ConnectionHandler() {
        userService = new UserService();
        gameService = new GameService();
        establishConnection();
        new ServerListener().start();
    }

    // Helpers
    private void establishConnection() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 5005);
            reader = new DataInputStream(socket.getInputStream());
            writer = new DataOutputStream(socket.getOutputStream());
            singleton.setServerStatus(true);
            System.out.println("Server status in establishConnection = " + singleton.getServerStatus());
        } catch (IOException e) {
//            e.printStackTrace();
            singleton.setServerStatus(false);
            System.out.println("Server status in establishConnection = " + singleton.getServerStatus());
        }
    }

    public void refreshConnection() {
        establishConnection();
        new ServerListener().start();
    }

    // Auth request
    public void sendLoginRequest(LoginUserDto loginUserDto) {
        if (socket.isConnected()) {
            userService.loginRequest(loginUserDto, writer);
        }
    }

    public void sendRegisterRequest(RegisterUserDto registerUserDto) {
        if (socket.isConnected()) {
            userService.registerRequest(registerUserDto, writer);
        }
    }

    public void sendLogoutRequest(LogoutUserDto logoutUserDto) {
        if (socket.isConnected()) {
            userService.logoutRequest(logoutUserDto, writer);
        }
    }

    public void sendGameInvitation(GameInvitationDto gameInvitationDto) {
        if (socket.isConnected()) {
            gameService.sendGameInvitation(gameInvitationDto, writer);
        }
    }

    private void responseHandler(String jsonString) {
        JsonObject response = JsonBuilder.toJsonObject(jsonString);
        Singleton singleton = Singleton.getInstance();
        System.out.println(response.get("operation").getAsString());

        switch (response.get("operation").getAsString()) {
            case "login" -> {
                singleton.setLoginStatus(response.get("result").getAsBoolean());
            }
            case "signUp" -> singleton.setCreateUserResponse(response.get("result").getAsBoolean());
            case "refreshUsers" -> singleton.setOnlineUsers(JsonBuilder.toUsersDtoList(response.get("onlineUsers").getAsJsonArray()));
            case "gameRequest" -> {
                singleton.setSenderName(response.get("playerReqName").getAsString());
            }
            case "player2Response" -> {
                singleton.setGameInvitationAnswer(response.get("answer").getAsBoolean());
                System.out.println("Game invitation answer");
            }
        }
    }

    public void closeConnection() {
        try {
            if (socket != null)
                socket.close();
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Client listener thread
    public class ServerListener extends Thread {
        @Override
        public void run() {
            if (socket != null) {
                while (socket.isConnected()) {
                    try {
                        String response = reader.readUTF();
                        System.out.println(response);
                        responseHandler(response);
                    } catch (SocketException socketException) {
                        System.out.println("socket has been closed");
                        break;
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }

        }
    }
}
