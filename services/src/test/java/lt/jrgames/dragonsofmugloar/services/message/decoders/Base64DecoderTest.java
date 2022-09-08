package lt.jrgames.dragonsofmugloar.services.message.decoders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base64DecoderTest {
    private final static String ENCODED_VALUE = "dGVzdA==";
    private final static String DECODED_VALUE = "test";

    @Test
    @DisplayName("Valid Base64 decoded")
    void decode() {
        Base64Decoder decoder = new Base64Decoder();

        String decoded = decoder.decode(ENCODED_VALUE);

        assertEquals(DECODED_VALUE, decoded);
    }

    @Test
    @DisplayName("Invalid Base64 decoded")
    void decodeInvalid() {
        Base64Decoder decoder = new Base64Decoder();

        assertThrows(IllegalArgumentException.class, () -> decoder.decode(ENCODED_VALUE + "_bad"));
    }

}