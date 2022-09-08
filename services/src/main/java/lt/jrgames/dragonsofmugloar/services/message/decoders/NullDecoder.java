package lt.jrgames.dragonsofmugloar.services.message.decoders;

public class NullDecoder implements Decoder {
    @Override
    public String decode(String value) {
        return value;
    }
}
