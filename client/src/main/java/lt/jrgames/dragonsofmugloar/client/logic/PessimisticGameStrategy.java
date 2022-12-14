package lt.jrgames.dragonsofmugloar.client.logic;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lt.jrgames.dragonsofmugloar.client.logic.actions.GameAction;
import lt.jrgames.dragonsofmugloar.client.logic.actions.PurchaseAction;
import lt.jrgames.dragonsofmugloar.client.logic.actions.SolveMessageAction;
import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.message.MessageService;
import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.Probability;
import lt.jrgames.dragonsofmugloar.services.shop.ShopService;
import lt.jrgames.dragonsofmugloar.services.shop.model.ShopItem;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Log4j2
public class PessimisticGameStrategy implements GameStrategy {
    public static final String HEALING_POTION_ID = "hpot";
    public static final String IGNORE_MESSAGE_PREFIX = "Steal";
    public static final int MAX_LIVES = 3;
    private static final Comparator<Message> messagesComparator = new MessageComparator().reversed();

    private MessageService messageService;
    private ShopService shopService;

    @Override
    public GameAction nextAction(GameContext context) {
        if (isHealNeeded(context)) {
            Optional<ShopItem> healItem = findHealItem(context);
            if (healItem.isPresent()) {
                return new PurchaseAction(shopService, healItem.get());
            }
        }

        Message messageToSolve = findMessageToSolve(context);
        Probability probability = messageToSolve.getProbability();

        if (isBuffNeeded(probability.getValue())) {
            Optional<ShopItem> bestBuff = findBestBuff(context);
            if (bestBuff.isPresent()) {
                return new PurchaseAction(shopService, bestBuff.get());
            }
        }

        return new SolveMessageAction(messageService, messageToSolve);
    }

    private boolean isHealNeeded(GameContext context) {
        return context.getLives() < MAX_LIVES;
    }

    private boolean isBuffNeeded(float probability) {
        return probability < Probability.RISKY.getValue();
    }

    private Message findMessageToSolve(GameContext context) {
        List<Message> messages = messageService.availableMessages(context.getId());
        messages.sort(messagesComparator);

        return messages.stream()
                .filter(message -> !message.getMessage().startsWith(IGNORE_MESSAGE_PREFIX))
                .findFirst()
                .orElse(messages.get(0));
    }

    private Optional<ShopItem> findHealItem(GameContext context) {
        return shopService.availableItems(context.getId()).stream()
                .filter(item -> item.getId().equals(HEALING_POTION_ID))
                .findAny().
                filter(healItem -> context.getGold() >= healItem.getCost());
    }

    private Optional<ShopItem> findBestBuff(GameContext context) {
        List<ShopItem> shopItems = shopService.availableItems(context.getId());
        shopItems.sort(Comparator.comparing(ShopItem::getCost).reversed());
        return shopItems.stream()
                .filter(item -> !item.getId().equals(HEALING_POTION_ID) && item.getCost() <= context.getGold())
                .findFirst();
    }
}
