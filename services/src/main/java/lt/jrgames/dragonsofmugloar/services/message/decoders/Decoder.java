package lt.jrgames.dragonsofmugloar.services.message.decoders;

@FunctionalInterface
public interface Decoder {
    String decode(String value);
}
