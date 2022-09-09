package lt.jrgames.dragonsofmugloar.services.message.model;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdNodeBasedDeserializer;
import lt.jrgames.dragonsofmugloar.services.message.decoders.Decoder;
import lt.jrgames.dragonsofmugloar.services.message.decoders.DecoderFactory;

import java.util.function.Function;

public class MessageDeserializer extends StdNodeBasedDeserializer<Message> {

    public static final String FIELD_ENCRYPTED = "encrypted";
    public static final String FIELD_AD_ID = "adId";
    public static final String FIELD_MESSAGE = "message";
    public static final String FIELD_EXPIRES_IN = "expiresIn";
    public static final String FIELD_REWARD = "reward";
    public static final String FIELD_PROBABILITY = "probability";

    private final transient DecoderFactory decoderFactory = new DecoderFactory();

    public MessageDeserializer() {
        this(null);
    }

    public MessageDeserializer(Class<Message> targetType) {
        super(targetType);
    }

    @Override
    public Message convert(JsonNode node, DeserializationContext ctx) {
        int encrypted = node.path(FIELD_ENCRYPTED).asInt();
        String adId = node.path(FIELD_AD_ID).asText(null);
        String message = node.path(FIELD_MESSAGE).asText(null);
        Integer expiresIn = nullableValue(node.path(FIELD_EXPIRES_IN), JsonNode::asInt);
        Integer reward = nullableValue(node.path(FIELD_REWARD), JsonNode::asInt);
        String probability = node.path(FIELD_PROBABILITY).asText(null);

        Decoder decoder = decoderFactory.getInstanceOf(encrypted);

        return Message.builder()
                .adId(decoder.decode(adId))
                .message(decoder.decode(message))
                .expiresIn(expiresIn)
                .reward(reward)
                .probability(Probability.convert(decoder.decode(probability)))
                .build();
    }

    private <T> T nullableValue(JsonNode node, Function<JsonNode, T> getter) {
        return node.isNull() ? null : getter.apply(node);
    }
}
