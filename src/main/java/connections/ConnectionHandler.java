package connections;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.Dtos.gameDtos.*;
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
        } catch (IOException e) {
            e.printStackTrace();
            singleton.setServerStatus(false);
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

    public void sendSaveGame(SaveGameDto saveGameDto) {
        if (socket.isConnected()) {
            gameService.sendSaveGame(saveGameDto, writer);
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
    public void sendPlayerMove(PlayerMoveDto playerMoveDto) {
        if (socket.isConnected()) {
            gameService.sendPlayerMove(playerMoveDto, writer);
        }
    }

    public void sendGameInvitationAnswer(GameInvitationAnswerDto gameInvitationAnswerDto) {
        if (socket.isConnected()) {
            gameService.sendGameInvitationAnswer(gameInvitationAnswerDto, writer);
        }
    }

    private void responseHandler(String jsonString) {
        JsonObject response = JsonBuilder.toJsonObject(jsonString);
        Singleton singleton = Singleton.getInstance();

        if (response.get("operation") != null)
            switch (response.get("operation").getAsString()) {
                case "login" -> singleton.setLoginStatus(response.get("result").getAsBoolean());
                case "signUp" -> singleton.setCreateUserResponse(response.get("result").getAsBoolean());
                case "refreshUsers" -> singleton.setOnlineUsers(JsonBuilder.toUsersDtoList(response.get("onlineUsers").getAsJsonArray()));
                case "player2Response" -> {
                    GameInvitationAnswerDto gameInvitationAnswerDto = new GameInvitationAnswerDto();
                    gameInvitationAnswerDto.setAnswer(response.get("answer").getAsBoolean());
                    singleton.setGameInvitationAnswerDto(gameInvitationAnswerDto);
                }
                case "receiveInvitation" -> {
                    ReceiveGameInvitationDto gameInvitationDto = new ReceiveGameInvitationDto();
                    gameInvitationDto.setOpponentName(response.get("opponentName").getAsString());
                    singleton.setGameInvitationDto(gameInvitationDto);
                }
                case "getCreatedGame" -> {
                    CreatedGameDto createdGameDto = new CreatedGameDto();
                    createdGameDto.setGameId(response.get("gameId").getAsInt());
                    createdGameDto.setPlayerX(response.get("playerX").getAsString());
                    createdGameDto.setPlayerO(response.get("playerO").getAsString());
                    singleton.setCreatedGameDto(createdGameDto);
                }
                case "playerMove" -> {
                    ReceivePlayerMoveDto receivePlayerMove = new ReceivePlayerMoveDto();
                    receivePlayerMove.setUserName(response.get("playerName").getAsString());
                    receivePlayerMove.setPosition(response.get("position").getAsString());
                    receivePlayerMove.setSign(response.get("sign").getAsString());
                    singleton.setReceivePlayerMoveDto(receivePlayerMove);
                    Button selectedButton = singleton.getButtons().stream().filter(button ->
                            button.getId().split("n")[1].equals(receivePlayerMove.getPosition())).findFirst().get();
                    Platform.runLater(() -> {
                        selectedButton.fire();
                    });
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
                        System.out.println("from stream " + response);
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
