package model.Dtos.userDtos;

public class RegisterUserDto {
    private String userName;

    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String useName) {
        this.userName = useName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
