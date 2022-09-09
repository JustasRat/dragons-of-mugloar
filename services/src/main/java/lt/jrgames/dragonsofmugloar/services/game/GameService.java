package lt.jrgames.dragonsofmugloar.services.game;

import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GameService", url = "${service.url}")
public interface GameService {
    @PostMapping("/api/v2/game/start")
    Game start();
}
