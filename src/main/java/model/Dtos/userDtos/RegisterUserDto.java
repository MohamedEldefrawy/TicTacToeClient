package model.Dtos.userDtos;

public class RegisterUserDto {
    private String useName;
    private String password;
    private String confiormPassword;

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfiormPassword() {
        return confiormPassword;
    }

    public void setConfiormPassword(String confiormPassword) {
        this.confiormPassword = confiormPassword;
    }
}
