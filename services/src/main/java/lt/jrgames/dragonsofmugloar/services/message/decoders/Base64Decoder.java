package lt.jrgames.dragonsofmugloar.services.message.decoders;

import java.util.Base64;

public class Base64Decoder implements Decoder {
    @Override
    public String decode(String value) {
        return new String(Base64.getDecoder().decode(value));
    }
}
