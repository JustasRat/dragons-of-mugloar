package lt.jrgames.dragonsofmugloar.services.shop.model;

import lombok.Data;

@Data
public class PurchaseResult {
    private boolean success;
    private Integer lives;
    private Long gold;
    private Long score;
    private Long highScore;
    private Integer turn;
    private String message;
}
