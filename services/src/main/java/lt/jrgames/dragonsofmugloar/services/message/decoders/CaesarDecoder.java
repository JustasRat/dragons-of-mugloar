package lt.jrgames.dragonsofmugloar.services.message.decoders;

public class CaesarDecoder implements Decoder {
    public static final char DEFAULT_UPPER_CHAR = 'A';
    public static final char DEFAULT_LOWER_CHAR = 'a';
    private final static int OFFSET = 13;

    @Override
    public String decode(String value) {
        StringBuilder result = new StringBuilder();

        for (char symbol : value.toCharArray()) {
            result.append(decode(symbol));
        }
        return result.toString();
    }

    private char decode(char value) {
        if (!Character.isAlphabetic(value)) {
            return value;
        }
        int defaultChar = Character.isUpperCase(value) ? DEFAULT_UPPER_CHAR : DEFAULT_LOWER_CHAR;
        int position = value - defaultChar;
        int newPosition = (position + OFFSET) % 26;
        return (char) (defaultChar + newPosition);
    }
}
