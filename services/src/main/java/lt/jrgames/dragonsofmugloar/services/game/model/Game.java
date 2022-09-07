package lt.jrgames.dragonsofmugloar.services.game.model;

import lombok.Data;

@Data
public class Game {
    private String gameId;
    private int lives;
    private long gold;
    private int level;
    private long score;
    private long highScore;
    private int turn;
}
