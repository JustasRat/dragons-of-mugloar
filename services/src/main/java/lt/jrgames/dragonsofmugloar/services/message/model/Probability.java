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
    UNKNOWN(null),
    IMPOSSIBLE("Impossible"),
    SUICIDE_MISSION("Suicide mission"),
    RATHER_DETRIMENTAL("Rather detrimental"),
    PLAYING_WITH_FIRE("Playing with fire"),
    RISKY("Risky"),
    GAMBLE("Gamble"),
    HMMM("Hmmm...."),
    QUITE_LIKELY("Quite likely"),
    PIECE_OF_CAKE("Piece of cake"),
    WALK_IN_THE_PARK("Walk in the park"),
    SURE_THING("Sure thing");

    private final String value;

    private static final Map<String, Probability> probabilityMap = Arrays.stream(Probability.values())
            .collect(Collectors.toMap(Probability::getValue, Function.identity()));

    public static Probability convert(String probability) {
        return Optional.ofNullable(probability)
                .map(Probability::getOrThrow)
                .orElse(UNKNOWN);
    }

    private static Probability getOrThrow(String probability) {
        return Optional.ofNullable(probabilityMap.get(probability))
                .orElseThrow(() -> new IllegalArgumentException("Unknown probability " + probability));
    }
}
