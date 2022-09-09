package lt.jrgames.dragonsofmugloar.client.logic;

import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.Probability;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageComparatorTest {

    private final MessageComparator comparator = new MessageComparator();

    @Test
    void orderByProbability() {
        List<Message> messages = new ArrayList<>(List.of(
                create(10, Probability.PIECE_OF_CAKE),
                create(10, Probability.SURE_THING),
                create(10, Probability.IMPOSSIBLE)
        ));

        messages.sort(comparator);

        assertEquals(Probability.IMPOSSIBLE, messages.get(0).getProbability());
        assertEquals(Probability.PIECE_OF_CAKE, messages.get(1).getProbability());
        assertEquals(Probability.SURE_THING, messages.get(2).getProbability());
    }

    @Test
    void orderByProbabilityAndReward() {
        List<Message> messages = new ArrayList<>(List.of(
                create(10, Probability.PIECE_OF_CAKE),
                create(10, Probability.SURE_THING),
                create(5, Probability.PIECE_OF_CAKE),
                create(10, Probability.IMPOSSIBLE)
        ));

        messages.sort(comparator);

        assertEquals(Probability.PIECE_OF_CAKE, messages.get(1).getProbability());
        assertEquals(5, messages.get(1).getReward());
        assertEquals(Probability.PIECE_OF_CAKE, messages.get(2).getProbability());
        assertEquals(10, messages.get(2).getReward());
    }

    private Message create(Integer reward, Probability probability) {
        return Message.builder()
                .reward(reward)
                .probability(probability)
                .build();
    }
}