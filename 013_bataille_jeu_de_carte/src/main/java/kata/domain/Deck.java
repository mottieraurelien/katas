package kata.domain;

import java.util.Stack;

import static java.util.Collections.shuffle;
import static java.util.stream.Stream.of;

public class Deck {

    private final Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();
        of(Color.values())
            .forEach(color -> of(Figure.values()).forEach(figure -> this.cards.add(new Card(color, figure))));
        shuffle(this.cards);
    }

    boolean isEmpty() {
        return this.cards.empty();
    }

    int size() {
        return this.cards.size();
    }

    Card pop() {
        return this.cards.pop();
    }

}