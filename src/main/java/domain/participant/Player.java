package domain.participant;

import domain.GameResult;
import domain.HitThreshold;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Player extends Participant {

    public Player(final String name) {
        super(HitThreshold.PLAYER_THRESHOLD, name);
    }

    public String getName() {
        return name;
    }

    public Map<String, GameResult> getGameResultWithName(final Participant other) {
        return Map.of(name, cards.calculateGameResult(other.cards));
    }
}
