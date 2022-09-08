package lt.jrgames.dragonsofmugloar.services.message.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MessageDeserializerTest {
    private static final String FILE_MESSAGE = "message.json";
    private static final String FILE_MESSAGE_NULLS = "message-nulls.json";
    private static final String FILE_MESSAGE_BASE64 = "message-base64.json";
    private static final String FILE_MESSAGE_CAESAR = "message-caesar.json";

    @Test
    @DisplayName("Deserialize plain message")
    void plainMessage() throws IOException {
        MessageDeserializer deserializer = new MessageDeserializer();
        JsonNode jsonNode = readJson(FILE_MESSAGE);

        Message message = deserializer.convert(jsonNode, null);

        assertEquals("zG4oPeOw", message.getAdId());
        assertEquals("Help Olof Kitchen to write their biographical novel about their difficulties with a deranged turnips", message.getMessage());
        assertEquals(32, message.getReward());
        assertEquals(7, message.getExpiresIn());
        assertEquals(Probability.GAMBLE, message.getProbability());
    }

    @Test
    @DisplayName("Deserialize Base64 message")
    void base64Message() throws IOException {
        MessageDeserializer deserializer = new MessageDeserializer();
        JsonNode jsonNode = readJson(FILE_MESSAGE_BASE64);

        Message message = deserializer.convert(jsonNode, null);

        assertEquals("lfjt4lo0", message.getAdId());
        assertEquals("Investigate Vanja William and find out their relation to the magic horse.", message.getMessage());
        assertEquals(165, message.getReward());
        assertEquals(3, message.getExpiresIn());
        assertEquals(Probability.RISKY, message.getProbability());
    }

    @Test
    @DisplayName("Deserialize Caesar message")
    void caesarMessage() throws IOException {
        MessageDeserializer deserializer = new MessageDeserializer();
        JsonNode jsonNode = readJson(FILE_MESSAGE_CAESAR);

        Message message = deserializer.convert(jsonNode, null);

        assertEquals("l5uXZTHm", message.getAdId());
        assertEquals("Kill Harold Derrickson with chariot and make Ksawery Stafford from keep in Metalcrest to take the blame", message.getMessage());
        assertEquals(80, message.getReward());
        assertEquals(2, message.getExpiresIn());
        assertEquals(Probability.IMPOSSIBLE, message.getProbability());
    }

    @Test
    @DisplayName("Deserialize plain message with null values")
    void plainMessageWithNull() throws IOException {
        MessageDeserializer deserializer = new MessageDeserializer();
        JsonNode jsonNode = readJson(FILE_MESSAGE_NULLS);

        Message message = deserializer.convert(jsonNode, null);

        assertNull(message.getAdId());
        assertNull(message.getMessage());
        assertEquals(32, message.getReward());
        assertNull(message.getExpiresIn());
        assertEquals(Probability.UNKNOWN, message.getProbability());
    }

    private JsonNode readJson(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(ClassLoader.getSystemResourceAsStream(fileName));
    }
}