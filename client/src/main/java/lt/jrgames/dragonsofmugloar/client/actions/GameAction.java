package lt.jrgames.dragonsofmugloar.client.actions;

import lt.jrgames.dragonsofmugloar.client.model.GameContext;

@FunctionalInterface
public interface GameAction {

    GameContext perform(GameContext context);
}
