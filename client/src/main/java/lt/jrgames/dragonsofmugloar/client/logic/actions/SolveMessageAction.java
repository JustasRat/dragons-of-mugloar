package lt.jrgames.dragonsofmugloar.client.logic.actions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.message.MessageService;
import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;

@Log4j2
@AllArgsConstructor
public class SolveMessageAction implements GameAction {

    private MessageService messageService;
    private Message message;

    @Override
    public GameContext perform(GameContext context) {
        MessageResult result = messageService.solve(context.getId(), message.getAdId());

        if (result.isSuccess()) {
            log.info("Solved message: id={}, reward={}, probability={}",
                    message.getAdId(), message.getReward(), message.getProbability());
        } else {
            log.info("Failed to solve message: reason={}, id={}, reward={}, probability={}",
                    result.getMessage(), message.getAdId(), message.getReward(), message.getProbability());
        }
        return new GameContext(context, result);
    }
}
