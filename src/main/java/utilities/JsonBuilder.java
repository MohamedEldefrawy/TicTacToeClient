package utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Dtos.gameDtos.*;
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

    public static String sendPlayerMove(PlayerMoveDto playerMoveDto) {
        JsonObject sendPlayerMove = new JsonObject();
        sendPlayerMove.addProperty("operation", RequestTypes.playerMove.toString());
        sendPlayerMove.addProperty("playerName", playerMoveDto.getPlayerName());
        sendPlayerMove.addProperty("position", playerMoveDto.getPosition());
        sendPlayerMove.addProperty("sign", playerMoveDto.getSign());
        sendPlayerMove.addProperty("gameState", playerMoveDto.getGameState());
        sendPlayerMove.addProperty("gameId", playerMoveDto.getGameId());
        return sendPlayerMove.toString();
    }

    public static String sendSaveGame(SaveGameRequestDto saveGameDto) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("operation", RequestTypes.SaveGameInvitation.toString());
        jsonObject.addProperty("opponentName", saveGameDto.getOpponentName());
        return jsonObject.toString();
    }

    public static String sendSaveGameResponse(SaveGameResponseDto saveGameResponse) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("operation", RequestTypes.SaveGameInvitationResponse.toString());
        jsonObject.addProperty("answer", saveGameResponse.isAnswer());
        return jsonObject.toString();
    }

    public static String finishGameRequest(FinishGameDto finishGameDto) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("operation", RequestTypes.gameFinished.toString());
        jsonObject.addProperty("gameId", finishGameDto.getGameId());
        jsonObject.addProperty("isFinished", finishGameDto.isFinished());
        jsonObject.addProperty("winner", finishGameDto.getWinnerName());
        return jsonObject.toString();
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
