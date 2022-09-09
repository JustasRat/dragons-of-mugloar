package lt.jrgames.dragonsofmugloar.services.shop.model;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class PurchaseResult {
    private boolean shoppingSuccess;
    private int lives;
    private int gold;
    private int turn;
    private int level;

}
