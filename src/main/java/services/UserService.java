package services;

import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;
import model.Dtos.userDtos.UserDto;
import utilities.JsonBuilder;

import java.io.BufferedWriter;
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
    public void loginRequest(LoginUserDto loginUserDto, BufferedWriter writer) {
        String request = JsonBuilder.loginRequest(loginUserDto);
        try {
            writer.write(request);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerRequest(RegisterUserDto registerUserDto, BufferedWriter writer) {
        String request = JsonBuilder.registerRequest(registerUserDto);
        try {
            writer.write(request);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutRequest(LogoutUserDto logoutUserDto, BufferedWriter writer) {
        String request = JsonBuilder.logoutRequest(logoutUserDto);
        try {
            writer.write(request);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}