package model.Dtos.gameDtos;

public class CreatedGameDto {
    private int gameId;
    private String playerX;
    private String playerY;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPlayerX() {
        return playerX;
    }

    public void setPlayerX(String playerX) {
        this.playerX = playerX;
    }

    public String getPlayerY() {
        return playerY;
    }

    public void setPlayerY(String playerY) {
        this.playerY = playerY;
    }
}
