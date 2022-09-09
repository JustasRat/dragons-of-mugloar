package lt.jrgames.dragonsofmugloar.services.message.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Probability {
    UNKNOWN(null, 0.5f),
    IMPOSSIBLE("Impossible", 0f),
    SUICIDE_MISSION("Suicide mission", 0.1f),
    RATHER_DETRIMENTAL("Rather detrimental", 0.2f),
    PLAYING_WITH_FIRE("Playing with fire", 0.3f),
    RISKY("Risky", 0.4f),
    GAMBLE("Gamble", 0.5f),
    HMMM("Hmmm....", 0.6f),
    QUITE_LIKELY("Quite likely", 0.7f),
    PIECE_OF_CAKE("Piece of cake", 0.8f),
    WALK_IN_THE_PARK("Walk in the park", 0.9f),
    SURE_THING("Sure thing", 1f);

    private final String title;
    private final float probability;

    private static final Map<String, Probability> probabilityMap = Arrays.stream(Probability.values())
            .collect(Collectors.toMap(Probability::getTitle, Function.identity()));

    public static Probability convert(String probability) {
        return Optional.ofNullable(probability)
                .map(Probability::getOrThrow)
                .orElse(UNKNOWN);
    }

    private static Probability getOrThrow(String title) {
        return Optional.ofNullable(probabilityMap.get(title))
                .orElseThrow(() -> new IllegalArgumentException("Unknown probability " + title));
    }
}
