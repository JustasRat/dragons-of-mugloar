package lt.jrgames.dragonsofmugloar.services.shop;

import lt.jrgames.dragonsofmugloar.services.shop.model.PurchaseResult;
import lt.jrgames.dragonsofmugloar.services.shop.model.ShopItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "ShopService", url = "${service.url}")
public interface ShopService {
    @GetMapping("/api/v2/{gameId}/shop")
    List<ShopItem> availableItems(@PathVariable String gameId);

    @PostMapping("/api/v2/{gameId}/shop/buy/{itemId}")
    PurchaseResult buyItem(@PathVariable String gameId, @PathVariable String itemId);
}
