package lt.jrgames.dragonsofmugloar.client.logic.actions;

import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.shop.ShopService;
import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.ShopItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseActionTest {

    @Mock
    private ShopService shopService;

    private final GameContext context = GameContext.builder()
            .id("test_game_id")
            .build();

    @Test
    void actionPerformed() {
        ShopItem item = ShopItem.builder()
                .id("test_item_id")
                .name("test_name")
                .cost(999)
                .build();

        when(shopService.buyItem("test_game_id", "test_item_id")).thenReturn(PurchaseResult.builder()
                .shoppingSuccess(true)
                .lives(5)
                .level(7)
                .build());
        PurchaseAction action = new PurchaseAction(shopService, item);

        GameContext updatedContext = action.perform(context);

        verify(shopService).buyItem("test_game_id", "test_item_id");
        assertEquals("test_game_id", updatedContext.getId());
        assertEquals(5, updatedContext.getLives());
        assertEquals(7, updatedContext.getLevel());
    }
}