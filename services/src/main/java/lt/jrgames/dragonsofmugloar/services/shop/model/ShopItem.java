package lt.jrgames.dragonsofmugloar.services.shop.model;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class ShopItem {
    private String id;
    private String name;
    private int cost;
}
