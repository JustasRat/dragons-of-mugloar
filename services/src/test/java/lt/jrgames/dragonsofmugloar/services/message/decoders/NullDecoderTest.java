package lt.jrgames.dragonsofmugloar.services.message.decoders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullDecoderTest {

    @Test
    void sameValueIsReturned() {
        Decoder decoder = new NullDecoder();

        String decoded = decoder.decode("test 123");

        assertEquals("test 123", decoded);
    }
}