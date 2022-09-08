package lt.jrgames.dragonsofmugloar.services.game.model;

import lombok.Data;

@Data
public class Game {
    private String gameId;
    private Integer lives;
    private Long gold;
    private Integer level;
    private Long score;
    private Long highScore;
    private Integer turn;
}
