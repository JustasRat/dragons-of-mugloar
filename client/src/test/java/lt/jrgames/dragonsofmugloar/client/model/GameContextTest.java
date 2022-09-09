package lt.jrgames.dragonsofmugloar.client.model;

import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameContextTest {

    private final GameContext defaultContext = GameContext.builder()
            .id("game_id")
            .lives(7)
            .score(561)
            .level(343)
            .turn(5)
            .gold(567)
            .build();

    @Test
    void initializeFromGameData() {
        GameContext context = new GameContext(Game.builder()
                .gameId("game_id")
                .lives(7)
                .score(561)
                .level(343)
                .turn(5)
                .gold(567)
                .highScore(9999)
                .build());

        assertEquals("game_id", context.getId());
        assertEquals(7, context.getLives());
        assertEquals(5, context.getTurn());
        assertEquals(567, context.getGold());
        assertEquals(561, context.getScore());
        assertEquals(343, context.getLevel());
    }

    @Test
    void initializeFromSolveResult() {
        GameContext context = new GameContext(defaultContext, MessageResult.builder()
                .success(true)
                .message("Hello")
                .lives(3)
                .score(233)
                .turn(1)
                .gold(67)
                .highScore(8888)
                .build());

        assertEquals("game_id", context.getId());
        assertEquals(3, context.getLives());
        assertEquals(1, context.getTurn());
        assertEquals(67, context.getGold());
        assertEquals(233, context.getScore());
        assertEquals(343, context.getLevel());
    }

    @Test
    void initializeFromPurchaseResult() {
        GameContext context = new GameContext(defaultContext, PurchaseResult.builder()
                .shoppingSuccess(true)
                .lives(2)
                .turn(2)
                .gold(135)
                .level(666)
                .build());

        assertEquals("game_id", context.getId());
        assertEquals(2, context.getLives());
        assertEquals(2, context.getTurn());
        assertEquals(135, context.getGold());
        assertEquals(561, context.getScore());
        assertEquals(666, context.getLevel());
    }
}