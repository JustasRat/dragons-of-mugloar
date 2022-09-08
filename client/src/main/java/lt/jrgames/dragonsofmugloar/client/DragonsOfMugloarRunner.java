package lt.jrgames.dragonsofmugloar.client;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lt.jrgames.dragonsofmugloar.client.actions.GameAction;
import lt.jrgames.dragonsofmugloar.client.logic.GameStrategy;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.game.GameService;
import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@AllArgsConstructor
public class DragonsOfMugloarRunner implements CommandLineRunner {

    private GameService gameService;
    private GameStrategy strategy;

    @Override
    public void run(String... args) {
        Game newGame = gameService.start();
        log.info("=====Dragons of Mugloar ({}) =====", newGame.getGameId());

        GameContext context = new GameContext(newGame);
        while (context.getLives() > 0) {
            GameAction action = strategy.nextAction(context);
            context = action.perform(context);
            log.info("End of turn {}: lives={}, score={}, gold={}, level={}",
                    context.getTurn(), context.getLives(), context.getScore(), context.getGold(), context.getLevel());
        }
        log.info("===== End of game (score {} in {} turns) =====", context.getScore(), context.getTurn());
    }
}
