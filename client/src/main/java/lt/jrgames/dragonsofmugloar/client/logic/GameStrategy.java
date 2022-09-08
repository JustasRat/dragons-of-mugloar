package lt.jrgames.dragonsofmugloar.client.logic;

import lt.jrgames.dragonsofmugloar.client.actions.GameAction;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;

public interface GameStrategy {

    GameAction nextAction(GameContext context);
}
