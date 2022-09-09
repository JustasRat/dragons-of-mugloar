package lt.jrgames.dragonsofmugloar.client.logic;

import lt.jrgames.dragonsofmugloar.client.logic.actions.GameAction;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;

/**
 * Algorithm which decides next game action according to current game context.
 */
public interface GameStrategy {
    GameAction nextAction(GameContext context);
}
