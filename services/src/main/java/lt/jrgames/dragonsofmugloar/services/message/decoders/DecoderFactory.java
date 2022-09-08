package lt.jrgames.dragonsofmugloar.services.message.decoders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DecoderFactory {
    private final static int TYPE_NONE = 0;
    private final static int TYPE_BASE64 = 1;
    private final static int TYPE_SHIFT = 2;

    private final Map<Integer, Decoder> decoders = new HashMap<>();

    public DecoderFactory() {
        decoders.put(TYPE_NONE, new NullDecoder());
        decoders.put(TYPE_BASE64, new Base64Decoder());
        decoders.put(TYPE_SHIFT, new CaesarDecoder());
    }

    public Decoder getInstanceOf(int type) {
        return Optional.ofNullable(decoders.get(type))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported encoding type " + type));

    }
}
