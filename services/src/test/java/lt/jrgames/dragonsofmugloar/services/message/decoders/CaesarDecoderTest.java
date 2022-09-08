package lt.jrgames.dragonsofmugloar.services.message.decoders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarDecoderTest {
    private final static String ENCODED_VALUE = "grfg grfg grfg";
    private final static String DECODED_VALUE = "test test test";
    public static final String NON_ALPHABETICAL_CHARACTERS = "~!@#$%^&*()_+1234567890-=";

    @Test
    @DisplayName("Alphabetical character shift encoded value")
    void decode() {
        CaesarDecoder decoder = new CaesarDecoder();

        String decoded = decoder.decode(ENCODED_VALUE);

        assertEquals(DECODED_VALUE, decoded);
    }

    @Test
    @DisplayName("Non Alphabetical character ignored")
    void decodeNotAlphabetical() {
        CaesarDecoder decoder = new CaesarDecoder();

        String decoded = decoder.decode(ENCODED_VALUE + NON_ALPHABETICAL_CHARACTERS);

        assertEquals(DECODED_VALUE + NON_ALPHABETICAL_CHARACTERS, decoded);
    }

    @Test
    @DisplayName("Wrapping shift")
    void wrappingShift() {
        CaesarDecoder decoder = new CaesarDecoder();

        String decoded = decoder.decode("U");

        assertEquals("H", decoded);
    }

}