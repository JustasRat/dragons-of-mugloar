package lt.jrgames.dragonsofmugloar.services.shop.model;

import lombok.Data;

@Data
public class PurchaseResult {
    private boolean success;
    private int lives;
    private long gold;
    private long score;
    private long highScore;
    private int turn;
    private String message;
}
