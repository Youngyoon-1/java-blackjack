package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Players {
    private final List<Player> players;

    public Players(List<String> names) {
        this.players = names
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void receiveCard() {
        for (Player player : players) {
            player.receiveCard(CardDeck.draw());
        }
    }

    public Map<String, List<Card>> getCardsWithName() {
        final Map<String, List<Card>> cardsWithNameTotal = new LinkedHashMap<>();
        for (Player player : players) {
            assert player.getCardsWithName() != null;
            cardsWithNameTotal.putAll(player.getCardsWithName());
        }
        return cardsWithNameTotal;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Map<String, Integer> getTotalScoreWithName() {
        final Map<String, Integer> totalScoreWithName = new LinkedHashMap<>();
        for (Player player : players) {
            totalScoreWithName.putAll(player.getTotalScoreWithName());
        }
        return totalScoreWithName;
    }

    public Map<String, GameResult> calculateGameResult(Dealer dealer) {
        final Map<String, GameResult> gameResultWithName = new LinkedHashMap<>();
        for (Player player : players) {
            gameResultWithName.putAll(player.getGameResultWithName(dealer));
        }
        return gameResultWithName;
    }
}