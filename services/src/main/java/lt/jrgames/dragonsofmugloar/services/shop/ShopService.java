package lt.jrgames.dragonsofmugloar.services.shop;

import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.ShopItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "ShopService", url = "${service.url}")
public interface ShopService {
    @RequestMapping(method = GET, value = "/api/v2/{gameId}/shop")
    List<ShopItem> shopItems(@PathVariable String gameId);

    @RequestMapping(method = POST, value = "/api/v2/{gameId}/shop/buy/{itemId}")
    PurchaseResult buyItem(@PathVariable String gameId, @PathVariable String itemId);
}
