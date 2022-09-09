package lt.jrgames.dragonsofmugloar.client.logic;

import lt.jrgames.dragonsofmugloar.services.message.model.Message;

import java.util.Comparator;

/**
 * Orders messages in ascending order by it`s probability and reward size.
 */
public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message message1, Message message2) {
        int probabilityOrder = Float.compare(message1.getProbability().getValue(), message2.getProbability().getValue());
        return probabilityOrder == 0 ? Long.compare(message1.getReward(), message2.getReward()) : probabilityOrder;
    }
}
