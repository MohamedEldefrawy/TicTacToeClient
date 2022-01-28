package utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Dtos.userDtos.LoginUserDto;
import model.Dtos.userDtos.LogoutUserDto;
import model.Dtos.userDtos.RegisterUserDto;

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

    //  handle request
    public static JsonObject toJsonObject(String jsonString) {
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

}
