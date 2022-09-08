package lt.jrgames.dragonsofmugloar.client.logic;

import lt.jrgames.dragonsofmugloar.services.message.model.Message;

import java.util.Comparator;

/**
 * Orders messages in ascending order by it`s probability and reward size.
 */
public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message message1, Message message2) {
        int probabilityOrder = message1.getProbability().compareTo(message2.getProbability());
        return probabilityOrder == 0 ? Long.compare(message1.getReward(), message2.getReward()) : probabilityOrder;
    }
}
