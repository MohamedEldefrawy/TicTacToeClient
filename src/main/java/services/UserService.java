package services;

import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;
import model.Dtos.userDtos.UserDto;
import utilities.JsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public class UserService {
    public String createPlayerIdCard(UserDto dto) {
        StringBuilder card = new StringBuilder();

        card.append(dto.getUserName()).append("\t");
        card.append("Wins:").append(dto.getWins()).append("\t");
        card.append("Losses:").append(dto.getLosses()).append("\t");
        card.append("Draws:").append(dto.getDraws()).append("\t");

        if (dto.isLoggedIn()) {
            card.append("online");
        } else {
            card.append("offline");
        }

        return card.toString();
    }

    // User Auth requests
    public void loginRequest(LoginUserDto loginUserDto, DataOutputStream writer) {
        String request = JsonBuilder.loginRequest(loginUserDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerRequest(RegisterUserDto registerUserDto, DataOutputStream writer) {
        String request = JsonBuilder.registerRequest(registerUserDto);
        try {
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutRequest(LogoutUserDto logoutUserDto, DataOutputStream writer) {
        String request = JsonBuilder.logoutRequest(logoutUserDto);
        try {
            System.out.println(request.toString());
            writer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}