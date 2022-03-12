package domain.participant;

import domain.CardScoreThreshold;
import domain.Cards;
import domain.card.Card;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Participant {
    protected final Cards cards;
    protected final String name;

    public Participant(final CardScoreThreshold canAddCardThreshold, final String name) {
        this.cards = new Cards(canAddCardThreshold);
        this.name = name;
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public boolean canReceiveCard() {
        return cards.canAddCard();
    }

    //    abstract public List<FinalGameResult> calculateFinalGameResult(Participant other);
    public Map<String, List<Card>> getCardsWithName() {
        return new LinkedHashMap<>(Map.of(name, getCards()));
    }

    protected List<Card> getCards() {
        return cards.getCards();
    }

    public Map<String, Integer> getTotalScoreWithName() {
        return new LinkedHashMap<>(Map.of(name, cards.calculateSum()));
    }
}