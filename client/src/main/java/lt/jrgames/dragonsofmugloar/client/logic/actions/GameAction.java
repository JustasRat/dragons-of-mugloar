package lt.jrgames.dragonsofmugloar.client.logic.actions;

import lt.jrgames.dragonsofmugloar.client.model.GameContext;

/**
 * Game action which can be executed against specific game context. Updated game context must be produced
 * which reflects performed action's side effects.
 */
@FunctionalInterface
public interface GameAction {
    GameContext perform(GameContext context);
}
