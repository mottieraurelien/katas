package kata.domain;

public class Card {

    private final Color color;
    private final Figure figure;

    Card(final Color color, final Figure figure) {
        this.color = color;
        this.figure = figure;
    }

    @Override
    public String toString() {
        return figure.name() + " - " + color.name();
    }

    Figure getFigure() {
        return this.figure;
    }

}