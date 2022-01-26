package connections;

import com.google.gson.JsonObject;
import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;
import services.UserService;
import utilities.JsonBuilder;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionHandler {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private UserService userService;

    public ConnectionHandler() {
        userService = new UserService();
        establishConnection();
        new ServerListener().start();
    }

    // Helpers
    private void establishConnection() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 2022);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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


    private void responseHandler(String jsonString) {
        JsonObject response = JsonBuilder.toJsonObject(jsonString);
        switch (response.get("operation").toString()) {
            case "login":  // Handle logic after login
                break;
            case "regiset":// handle login after register
                break;
            case "logout": // handle logic after logout
                break;
        }
    }

    // Client listener thread
    public class ServerListener extends Thread {
        public ServerListener() {
            start();
        }

        @Override
        public void run() {
            while (socket.isConnected()) {
                try {
                    String response = reader.readLine();
                    responseHandler(response);
                } catch (SocketException e) {
                    e.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
