package lt.jrgames.dragonsofmugloar.client.model;

import lombok.Value;
import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;

@Value
public class GameContext {
    String id;
    int lives;
    long gold;
    long score;
    int turn;
    int level;


    public GameContext(Game game) {
        id = game.getGameId();
        lives = game.getLives();
        gold = game.getGold();
        score = game.getScore();
        turn = game.getTurn();
        level = 0;
    }

    public GameContext(GameContext context, MessageResult messageResult) {
        id = context.getId();
        lives = messageResult.getLives();
        gold = messageResult.getGold();
        score = messageResult.getScore();
        turn = messageResult.getTurn();
        level = 0;
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
