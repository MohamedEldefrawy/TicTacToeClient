package utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Dtos.gameDtos.GameInvitationAnswerDto;
import model.Dtos.gameDtos.GameInvitationDto;
import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;
import model.Dtos.userDtos.UserDto;

import java.util.ArrayList;
import java.util.List;

public class JsonBuilder {

    // send request
    public static String loginRequest(LoginUserDto loginUserDto) {
        JsonObject loginRequest = new JsonObject();
        loginRequest.addProperty("operation", RequestTypes.login.toString());
        loginRequest.addProperty("user", loginUserDto.getUserName());
        loginRequest.addProperty("pass", loginUserDto.getPassword());
        return loginRequest.toString();
    }

    public static String registerRequest(RegisterUserDto registerUserDto) {
        JsonObject registerRequest = new JsonObject();
        registerRequest.addProperty("operation", RequestTypes.signUp.toString());
        registerRequest.addProperty("user", registerUserDto.getUserName());
        registerRequest.addProperty("pass", registerUserDto.getPassword());
        return registerRequest.toString();
    }

    public static String logoutRequest(LogoutUserDto logoutUserDto) {
        JsonObject logoutRequest = new JsonObject();
        logoutRequest.addProperty("operation", RequestTypes.logout.toString());
        logoutRequest.addProperty("user", logoutUserDto.getUserName());
        logoutRequest.addProperty("status", logoutUserDto.getStatus());
        return logoutRequest.toString();
    }

    public static String sendInvitation(GameInvitationDto gameInvitationDto) {
        JsonObject sendInvitation = new JsonObject();
        sendInvitation.addProperty("operation", RequestTypes.invitation.toString());
        sendInvitation.addProperty("user", gameInvitationDto.getUserName());
        sendInvitation.addProperty("player2", gameInvitationDto.getOpponentUserName());
        return sendInvitation.toString();
    }

    //  handle request
    public static JsonObject toJsonObject(String jsonString) {
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }


    // mapping jasonArray to List
    public static List<UserDto> toUsersDtoList(JsonArray jsonElements) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (JsonElement element : jsonElements) {
            UserDto tempUserDto = new UserDto();
            tempUserDto.setUserName(element.getAsJsonObject().get("userName").getAsString());
            tempUserDto.setWins(element.getAsJsonObject().get("wins").getAsInt());
            tempUserDto.setLosses(element.getAsJsonObject().get("losses").getAsInt());
            tempUserDto.setDraws(element.getAsJsonObject().get("draws").getAsInt());
            tempUserDto.setLoggedIn(element.getAsJsonObject().get("status").getAsBoolean());
            userDtoList.add(tempUserDto);
        }
        return userDtoList;
    }

    public static String sendInvitationAnswer(GameInvitationAnswerDto gameInvitationAnswerDto) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("operation", "invResponse");
        jsonObject.addProperty("answer", gameInvitationAnswerDto.getAnswer());
        return jsonObject.toString();
    }
}
