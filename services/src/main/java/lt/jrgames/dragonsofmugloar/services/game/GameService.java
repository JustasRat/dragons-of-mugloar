package lt.jrgames.dragonsofmugloar.services.game;

import lt.jrgames.dragonsofmugloar.services.game.model.Game;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "GameService", url = "${service.url}")
public interface GameService {
    @RequestMapping(method = POST, value = "/api/v2/game/start")
    Game start();
}
