package game.domain;

public class Coordinates {

    private final int abscissa;
    private final int ordinate;

    public Coordinates(final int abscissa, final int ordinate) {
        this.abscissa = abscissa;
        this.ordinate = ordinate;
    }

    public boolean closeAbscissa(final Coordinates otherCoordinates) {
        final int minExpectedAbscissa = this.abscissa - 1;
        final int maxExpectedAbscissa = this.abscissa + 1;
        return otherCoordinates.abscissa >= minExpectedAbscissa
                && otherCoordinates.abscissa <= maxExpectedAbscissa;
    }

    public boolean closeOrdinate(final Coordinates otherCoordinates) {
        final int minExpectedOrdinate = this.ordinate - 1;
        final int maxExpectedOrdinate = this.ordinate + 1;
        return otherCoordinates.ordinate >= minExpectedOrdinate
                && otherCoordinates.ordinate <= maxExpectedOrdinate;
    }

}