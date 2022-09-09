package lt.jrgames.dragonsofmugloar.client;

import lt.jrgames.dragonsofmugloar.client.logic.GameStrategy;
import lt.jrgames.dragonsofmugloar.client.logic.actions.GameAction;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.game.GameService;
import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DragonsOfMugloarRunnerTest {

    @Mock
    private GameService gameService;

    @Mock
    private GameStrategy strategy;

    @BeforeEach
    void setup() {
        when(gameService.start()).thenReturn(Game.builder()
                .gameId("game_id")
                .lives(5)
                .build());
        when(strategy.nextAction(any())).thenReturn(new SuicideAction());
    }

    @Test
    void executeWhileALive() {
        DragonsOfMugloarRunner runner = new DragonsOfMugloarRunner(gameService, strategy);

        runner.run();

        verify(strategy, times(5)).nextAction(any());
    }

    static class SuicideAction implements GameAction {
        @Override
        public GameContext perform(GameContext context) {
            return GameContext.builder()
                    .id(context.getId())
                    .lives(context.getLives() - 1)
                    .score(context.getScore())
                    .level(context.getLevel())
                    .turn(context.getTurn())
                    .build();
        }
    }
}