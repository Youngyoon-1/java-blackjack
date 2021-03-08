package blackjack.domain;

import java.util.function.Predicate;

public enum Outcome {
    WIN("승", result -> result == 1),
    LOSE("패", result -> result == -1),
    DRAW("무", result -> result == 0);

    private final String word;
    private final Predicate<Integer> expression;

    Outcome(String word, Predicate<Integer> expression) {
        this.word = word;
        this.expression = expression;
    }

    public String getWord() {
        return word;
    }
}