package lt.jrgames.dragonsofmugloar.services.message.model;

import lombok.Data;

@Data
public class MessageResult {
    private boolean success;
    private int lives;
    private long gold;
    private long score;
    private long highScore;
    private int turn;
    private String message;
}
