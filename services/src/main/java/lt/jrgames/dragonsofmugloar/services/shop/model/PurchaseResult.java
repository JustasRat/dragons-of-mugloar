package lt.jrgames.dragonsofmugloar.services.shop.model;

import lombok.Data;

@Data
public class PurchaseResult {
    private boolean shoppingSuccess;
    private Integer lives;
    private Long gold;
    private Integer turn;
    private int level;

}
