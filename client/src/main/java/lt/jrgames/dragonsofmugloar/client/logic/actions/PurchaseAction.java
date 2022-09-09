package lt.jrgames.dragonsofmugloar.client.logic.actions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.shop.ShopService;
import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.ShopItem;

@Log4j2
@AllArgsConstructor
public class PurchaseAction implements GameAction {
    private ShopService shopService;
    private ShopItem item;

    @Override
    public GameContext perform(GameContext context) {
        PurchaseResult result = shopService.buyItem(context.getId(), item.getId());

        if (result.isShoppingSuccess()) {
            log.info("Bought item: id={}, name={}, cost={}",
                    item.getId(), item.getName(), item.getCost());
        } else {
            log.info("Failed to buy item: id={}, name={}, cost={}",
                    item.getId(), item.getName(), item.getCost());
        }

        return new GameContext(context, result);
    }
}
