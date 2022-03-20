package blakjack.domain.participant;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.card.CardDeck;
import blakjack.domain.state.State;
import blakjack.domain.state.running.Init;

import java.util.List;

public abstract class Participant {
    protected State state;

    Participant(final PrivateArea privateArea, final Chip chip) {
        this.state = new Init(privateArea, chip);
    }

    abstract public void hit(Card card);

    public final void stay() {
        state = state.stay();
    }

    public final void initCards(final CardDeck cardDeck) {
        state = state.draw(cardDeck.draw());
        state = state.draw(cardDeck.draw());
    }

    public final String getName() {
        return state.getName();
    }

    public final List<Card> getCards() {
        return state.getCards();
    }

    public final int getTotalScore() {
        return state.getTotalScore();
    }

    public final int getProfit(final Participant participant) {
        //디리미터 법칙에 위배
        // 설계 단계에서 예상하지 못했던 문제
        return state.compare(participant.state).getProfit();
    }
}
