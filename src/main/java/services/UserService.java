package services;

import model.Dtos.userDtos.UserDto;

public class UserService {
    public String createPlayerIdCard(UserDto dto) {
        StringBuilder card = new StringBuilder();
        card.append(dto.getUserName() + "\t");
        card.append("Wins:   " + dto.getWins() + "\t");
        card.append("Losses: " + dto.getLosses() + "\t");
        card.append("Draws:  " + dto.getDraws() + "\t");
        if (dto.isLoggedIn()) {
            card.append("online");
        } else {
            card.append("offline");
        }

        return card.toString();
    }
}
