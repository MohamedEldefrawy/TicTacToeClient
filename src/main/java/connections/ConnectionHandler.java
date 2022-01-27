package connections;

import com.google.gson.JsonObject;
import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;
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
    private UserService userService;

    public ConnectionHandler() {
        userService = new UserService();
        establishConnection();
        new ServerListener().start();
    }

    // Helpers
    private void establishConnection() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 5005);
            reader = new DataInputStream(socket.getInputStream());
            writer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Auth request
    public void sendLoginRequest(LoginUserDto loginUserDto) {
        if (socket.isConnected()) {
            userService.loginRequest(loginUserDto, writer);
        }
    }

    public boolean sendRegisterRequest(RegisterUserDto registerUserDto) {
        boolean result = false;
        if (socket.isConnected()) {
            userService.registerRequest(registerUserDto, writer);
            result = true;
        }
        return result;
    }

    public void sendLogoutRequest(LogoutUserDto logoutUserDto) {
        if (socket.isConnected()) {
            userService.logoutRequest(logoutUserDto, writer);
        }
    }


    private void responseHandler(String jsonString) {
        JsonObject response = JsonBuilder.toJsonObject(jsonString);
        Singleton singleton = Singleton.getInstance();
        switch (response.get("operation").getAsString()) {
            case "login":
                singleton.setLoginStatus(response.get("result").getAsBoolean());
                System.out.println(singleton.getLoginStatus());
                break;
            case "register":// handle login after register
                break;
            case "logout": // handle logic after logout
                break;
        }
    }

    // Client listener thread
    public class ServerListener extends Thread {
        @Override
        public void run() {
            while (socket.isConnected()) {
                try {
                    String response = reader.readUTF();
                    System.out.println(response);
                    responseHandler(response);
                } catch (SocketException e) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
