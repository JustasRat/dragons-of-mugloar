package lt.jrgames.dragonsofmugloar.services.message.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbabilityTest {

    @Test
    @DisplayName("Convert valid probability")
    void validValue() {
        Probability probability = Probability.convert("Gamble");

        assertEquals(Probability.GAMBLE, probability);
    }

    @Test
    @DisplayName("Convert null")
    void nullValue() {
        Probability probability = Probability.convert(null);

        assertEquals(Probability.UNKNOWN, probability);
    }

    @Test
    @DisplayName("Convert invalid probability")
    void invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> Probability.convert("Invalid"));
    }

}