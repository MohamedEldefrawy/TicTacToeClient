package model.Dtos.gameDtos;

public class SaveGameRequestDto {


    private String requesterName;


    public String getOpponentName() {
        return requesterName;
    }

    public void setOpponentName(String requesterName) {
        this.requesterName = requesterName;
    }

}

