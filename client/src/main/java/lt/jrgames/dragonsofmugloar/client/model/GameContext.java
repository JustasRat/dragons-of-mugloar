package lt.jrgames.dragonsofmugloar.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;

@Value
@AllArgsConstructor
@Builder
public class GameContext {
    String id;
    int lives;
    int gold;
    int score;
    int turn;
    int level;

    public GameContext(Game game) {
        id = game.getGameId();
        lives = game.getLives();
        gold = game.getGold();
        score = game.getScore();
        turn = game.getTurn();
        level = game.getLevel();
    }

    public GameContext(GameContext context, MessageResult messageResult) {
        id = context.getId();
        lives = messageResult.getLives();
        gold = messageResult.getGold();
        score = messageResult.getScore();
        turn = messageResult.getTurn();
        level = context.getLevel();
    }

    public GameContext(GameContext context, PurchaseResult purchaseResult) {
        id = context.getId();
        lives = purchaseResult.getLives();
        gold = purchaseResult.getGold();
        score = context.getScore();
        turn = purchaseResult.getTurn();
        level = purchaseResult.getLevel();
    }
}
