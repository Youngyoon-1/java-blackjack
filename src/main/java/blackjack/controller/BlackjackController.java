package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.DealerDto;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.PlayerDto;
import blackjack.domain.player.PlayersDto;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void play() {
        Deck deck = initDeck();
        List<Gamer> gamers = initGamers(deck);
        Dealer dealer = initDealer(deck);
        OutputView.printGameStartMessage(DealerDto.from(dealer), PlayersDto.from(gamers));

        progressGamersHitOrStand(gamers, deck);
        progressDealerHitOrStand(dealer, deck);

        OutputView.printPlayersScoreInfo(PlayerDto.from(dealer), PlayersDto.from(gamers));
        GameResult gameResult = dealer.judgeGameResultWithGamers(gamers);
        OutputView.printGameResult(gameResult);
    }

    private Deck initDeck() {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Shape.values())
                .map(shape -> Card.of(denomination, shape)))
            .collect(Collectors.toList());
        Deck deck = new Deck(wholeCards);
        deck.shuffle();
        return deck;
    }

    private List<Gamer> initGamers(Deck deck) {
        String[] gamerNames = InputView.inputGamerNames();
        return Arrays.stream(gamerNames)
            .map(gamerName -> new Gamer(gamerName, Cards.of(deck.drawTwoStartCards())))
            .collect(Collectors.toList());
    }

    private Dealer initDealer(Deck deck) {
        return new Dealer(Cards.of(deck.drawTwoStartCards()));
    }

    private void progressGamersHitOrStand(List<Gamer> gamers, Deck deck) {
        for (Gamer gamer : gamers) {
            progressGamersHitOrStandInput(gamer, deck);
        }
    }

    private void progressGamersHitOrStandInput(Gamer gamer, Deck deck) {
        while(gamer.canDraw() && InputView.inputHitOrStand(gamer.getName())) {
            gamer.addCard(deck.draw());
            OutputView.printPlayerCardInfo(PlayerDto.from(gamer));
        }
    }

    private void progressDealerHitOrStand(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerOnemoreDrawedMessage();
        }
    }
}