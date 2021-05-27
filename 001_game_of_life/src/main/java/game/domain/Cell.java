package game.domain;

import java.util.List;

public class Cell {

    private final boolean alive;
    private final Coordinates coordinates;

    public Cell(final boolean alive, final Coordinates coordinates) {
        this.alive = alive;
        this.coordinates = coordinates;
    }

    public void evolve(final List<Cell> neighborhood) {

        // First, we need to know how many neighbors the cell has :
        final int numberOfNeighbors = this.countNeighbors(neighborhood);

        // The cell dies by solitude or overpopulation :
        if (numberOfNeighbors <= 1 || numberOfNeighbors > 3) {
            neighborhood.remove(this);
        }

    }

    private int countNeighbors(final List<Cell> neighborhood) {

        // "count" returns a long, that's why it's clearer to mapToInt and then sum.
        return neighborhood.stream()
                .filter(this::isNeighbor)
                .mapToInt(neighbor -> 1)
                .sum();

    }

    private boolean isNeighbor(final Cell anotherCell) {

        final Coordinates otherCoordinates = anotherCell.getCoordinates();

        final boolean closeAbscissa = this.coordinates.closeAbscissa(otherCoordinates);
        final boolean closeOrdinate = this.coordinates.closeOrdinate(otherCoordinates);

        return !this.equals(anotherCell) && closeAbscissa && closeOrdinate;

    }

    private Coordinates getCoordinates() {
        return this.coordinates;
    }

}