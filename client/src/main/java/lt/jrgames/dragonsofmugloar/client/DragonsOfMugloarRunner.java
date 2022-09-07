package lt.jrgames.dragonsofmugloar.client;

import lt.jrgames.dragonsofmugloar.services.game.GameService;
import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DragonsOfMugloarRunner implements CommandLineRunner {

    @Autowired
    private GameService gameService;

    @Override
    public void run(String... args) {
        System.out.println("Hello Mugloar");

        Game newGame = gameService.start();
        System.out.println("Game ID: " + newGame.getGameId());

    }
}
