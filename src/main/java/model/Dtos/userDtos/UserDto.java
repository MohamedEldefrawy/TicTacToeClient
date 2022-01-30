package model.Dtos.userDtos;

public class UserDto {

    private String userName;
    private int wins;
    private int losses;
    private int draws;
    private boolean isLoggedIn;

    public UserDto(String userName, int wins, int losses, int draws, boolean isLoggedIn) {
        this.userName = userName;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.isLoggedIn = isLoggedIn;
    }

    public UserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
