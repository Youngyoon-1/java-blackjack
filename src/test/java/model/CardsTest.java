package model;

import static model.CardSuit.DIAMOND;
import static model.CardSuit.HEART;
import static model.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void cannotReceiveCard() {
        final Card card1 = new Card(SPADE, CardFace.QUEEN);
        final Card card2 = new Card(HEART, CardFace.QUEEN);
        final Card card3 = new Card(DIAMOND, CardFace.QUEEN);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.canReceiveCard()).isFalse();
    }

    @Test
    void canReceiveCard() {
        final Card card1 = new Card(SPADE, CardFace.QUEEN);
        final Card card2 = new Card(HEART, CardFace.QUEEN);
        final Cards cards = new Cards(List.of(card1, card2));

        assertThat(cards.canReceiveCard()).isTrue();
    }

    @Test
    void canReceiveCardWithAce() {
        final Card card1 = new Card(SPADE, CardFace.EIGHT);
        final Card card2 = new Card(HEART, CardFace.ACE);
        final Card card3 = new Card(DIAMOND, CardFace.ACE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.canReceiveCard()).isTrue();
    }

    @Test
    void cannotReceiveCardWithAce() {
        final Card card1 = new Card(HEART, CardFace.ACE);
        final Card card2 = new Card(DIAMOND, CardFace.ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));
        assertThat(cards.canReceiveCard()).isFalse();
    }

    @Test
    void getSum() {
        final Card card1 = new Card(HEART, CardFace.ACE);
        final Card card2 = new Card(DIAMOND, CardFace.ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.getSum()).isEqualTo(21);
    }

    @Test
    void match() {
        final Card card1 = new Card(HEART, CardFace.ACE);
        final Card card2 = new Card(DIAMOND, CardFace.ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards bigger = new Cards(List.of(card1, card2, card3));

        final Card card4 = new Card(SPADE, CardFace.EIGHT);
        final Card card5 = new Card(HEART, CardFace.ACE);
        final Card card6 = new Card(DIAMOND, CardFace.ACE);
        final Cards smaller = new Cards(List.of(card4, card5, card6));

        assertThat(bigger.getResult(smaller)).isEqualTo(Result.WIN);
    }

    @Test
    void loseMatch() {
        final Card card4 = new Card(SPADE, CardFace.EIGHT);
        final Card card5 = new Card(HEART, CardFace.ACE);
        final Card card6 = new Card(DIAMOND, CardFace.ACE);
        final Cards notBust = new Cards(List.of(card4, card5, card6));

        final Card card1 = new Card(HEART, CardFace.KING);
        final Card card2 = new Card(DIAMOND, CardFace.JACK);
        final Card card3 = new Card(SPADE, CardFace.QUEEN);
        final Cards bust = new Cards(List.of(card1, card2, card3));
        assertThat(bust.getResult(notBust)).isEqualTo(Result.LOSE);
    }

    @Test
    void drawMatch() {
        final Card card1 = new Card(HEART, CardFace.KING);
        final Card card2 = new Card(DIAMOND, CardFace.JACK);
        final Card card3 = new Card(SPADE, CardFace.QUEEN);
        final Cards bust = new Cards(List.of(card1, card2, card3));

        final Card card4 = new Card(SPADE, CardFace.KING);
        final Card card5 = new Card(HEART, CardFace.JACK);
        final Card card6 = new Card(DIAMOND, CardFace.QUEEN);
        final Cards otherBust = new Cards(List.of(card4, card5, card6));
        assertThat(bust.getResult(otherBust)).isEqualTo(Result.DRAW);
    }
}