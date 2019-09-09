package kata.domain;

import java.util.List;
import java.util.Queue;

public class Player {

    private final String name;
    private final Queue<Card> hand;

    public Player(final String name, final Queue<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    public String toString() {
        return this.name + " -> " + this.hand;
    }

    Card playCard() {
        return this.hand.poll();
    }

    void receiveCard(final Card card) {
        this.hand.add(card);
    }

    void winsCards(final List<Card> cards) {
        this.hand.addAll(cards);
    }

    boolean hasNotAnyCardAnymore() {
        return this.hand.isEmpty();
    }

    int getScore() {
        return this.hand.stream().mapToInt(card -> card.getFigure().getValue()).sum();
    }

}