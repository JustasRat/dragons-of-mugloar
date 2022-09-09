package lt.jrgames.dragonsofmugloar.client.logic;

import lt.jrgames.dragonsofmugloar.client.logic.actions.GameAction;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.message.MessageService;
import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import lt.jrgames.dragonsofmugloar.services.message.model.Probability;
import lt.jrgames.dragonsofmugloar.services.shop.ShopService;
import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.ShopItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessimisticGameStrategyTest {

    @Mock
    private MessageService messageService;

    @Mock
    private ShopService shopService;

    @InjectMocks
    private PessimisticGameStrategy strategy;

    private GameContext context;

    @BeforeEach
    void setup() {
        context = GameContext.builder()
                .id("game_id")
                .turn(1)
                .lives(3)
                .gold(100)
                .score(10)
                .level(1)
                .build();
    }

    @Test
    @DisplayName("Solve message which has biggest probability and largest reward")
    void easiestMessageIsSolved() {
        when(messageService.availableMessages("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 10, Probability.RISKY),
                create("2", 10, Probability.PIECE_OF_CAKE),
                create("3", 50, Probability.PIECE_OF_CAKE)
        )));
        mockSolveResult("3");

        GameAction action = strategy.nextAction(context);
        action.perform(context);

        verify(messageService).solve("game_id", "3");
    }

    @Test
    @DisplayName("Heal if not maximum lives")
    void heal() {
        context = GameContext.builder()
                .id("game_id")
                .lives(1)
                .gold(100)
                .build();
        when(shopService.shopItems("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 100),
                create("hpot", 50)
        )));
        mockBuyResult("hpot");

        GameAction action = strategy.nextAction(context);
        action.perform(context);

        verify(shopService).buyItem("game_id", "hpot");
    }

    @Test
    @DisplayName("Skip heal if not enough gold")
    void skipHeal() {
        context = GameContext.builder()
                .id("game_id")
                .lives(1)
                .gold(20)
                .build();
        when(messageService.availableMessages("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 10, Probability.RISKY)
        )));
        when(shopService.shopItems("game_id")).thenReturn(new ArrayList<>(List.of(
                create("hpot", 50)
        )));
        mockSolveResult("1");

        GameAction action = strategy.nextAction(context);
        action.perform(context);

        verify(messageService).solve("game_id", "1");
    }

    @Test
    @DisplayName("Buy buff if message is risky to solve")
    void buyBuff() {
        when(messageService.availableMessages("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 10, Probability.SUICIDE_MISSION),
                create("2", 10, Probability.IMPOSSIBLE),
                create("3", 50, Probability.SUICIDE_MISSION)
        )));
        when(shopService.shopItems("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 90),
                create("2", 100)
        )));
        mockBuyResult("2");

        GameAction action = strategy.nextAction(context);
        action.perform(context);

        verify(shopService).buyItem("game_id", "2");
    }

    @Test
    @DisplayName("Skip buff if not enough gold")
    void skipBuff() {
        context = GameContext.builder()
                .id("game_id")
                .lives(3)
                .gold(20)
                .build();
        when(messageService.availableMessages("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 10, Probability.SUICIDE_MISSION)
        )));
        when(shopService.shopItems("game_id")).thenReturn(new ArrayList<>(List.of(
                create("1", 100)
        )));
        mockSolveResult("1");

        GameAction action = strategy.nextAction(context);
        action.perform(context);

        verify(messageService).solve("game_id", "1");
    }

    private void mockBuyResult(String itemId) {
        when(shopService.buyItem("game_id", itemId)).thenReturn(PurchaseResult.builder()
                .shoppingSuccess(true)
                .level(1)
                .gold(50)
                .build());
    }

    private void mockSolveResult(String messageId) {
        when(messageService.solve("game_id", messageId)).thenReturn(MessageResult.builder()
                .success(true)
                .message("Test message")
                .build());
    }

    private Message create(String id, int reward, Probability probability) {
        return Message.builder()
                .adId(id)
                .message("test")
                .reward(reward)
                .probability(probability)
                .build();
    }

    private ShopItem create(String id, int cost) {
        return ShopItem.builder()
                .id(id)
                .name("Random item")
                .cost(cost)
                .build();
    }
}