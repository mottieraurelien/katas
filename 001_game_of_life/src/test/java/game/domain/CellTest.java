package game.domain;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class CellTest {

    @Test
    void should_die_by_solitude_when_the_populated_cell_has_no_neighbor_at_all() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(singletonList(cell));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_die_by_solitude_when_the_populated_cell_has_no_close_neighbor() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(-2, 2)),
                new Cell(true, new Coordinates(-1, 2)),
                new Cell(true, new Coordinates(0, 2)),
                new Cell(true, new Coordinates(1, 2)),
                new Cell(true, new Coordinates(2, 2)),
                new Cell(true, new Coordinates(2, 1)),
                new Cell(true, new Coordinates(2, 0)),
                new Cell(true, new Coordinates(2, -1)),
                new Cell(true, new Coordinates(2, -2)),
                new Cell(true, new Coordinates(1, -2)),
                new Cell(true, new Coordinates(1, -2)),
                new Cell(true, new Coordinates(0, -2)),
                new Cell(true, new Coordinates(-1, -2)),
                new Cell(true, new Coordinates(-2, -2)),
                new Cell(true, new Coordinates(-2, -1)),
                new Cell(true, new Coordinates(-2, 0)),
                new Cell(true, new Coordinates(-2, 1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_die_by_solitude_when_the_populated_cell_has_one_neighbor() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(1, 1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_survive_when_the_populated_cell_has_two_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).contains(cell);

    }

    @Test
    void should_survive_when_the_populated_cell_has_three_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 1)),
                new Cell(true, new Coordinates(1, -1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).contains(cell);

    }

    @Test
    void should_appear_when_the_empty_cell_has_three_neighbors() {

        // TODO

    }

    @Test
    void should_die_by_overpopulation_when_the_populated_cell_has_four_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 0)),
                new Cell(true, new Coordinates(1, 1)),
                new Cell(true, new Coordinates(1, -1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_die_by_overpopulation_when_the_populated_cell_has_five_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 0)),
                new Cell(true, new Coordinates(1, 1)),
                new Cell(true, new Coordinates(1, -1)),
                new Cell(true, new Coordinates(-1, -1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_die_by_overpopulation_when_the_populated_cell_has_six_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 0)),
                new Cell(true, new Coordinates(1, 1)),
                new Cell(true, new Coordinates(1, -1)),
                new Cell(true, new Coordinates(-1, -1)),
                new Cell(true, new Coordinates(-1, 1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_die_by_overpopulation_when_the_populated_cell_has_seven_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 0)),
                new Cell(true, new Coordinates(1, 1)),
                new Cell(true, new Coordinates(1, -1)),
                new Cell(true, new Coordinates(-1, -1)),
                new Cell(true, new Coordinates(-1, 0)),
                new Cell(true, new Coordinates(-1, 1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

    @Test
    void should_die_by_overpopulation_when_the_populated_cell_has_eight_neighbors() {

        // [Arrange]
        final Cell cell = new Cell(true, new Coordinates(0, 0));
        final List<Cell> neighborhood = new LinkedList<>(asList(
                new Cell(true, new Coordinates(0, -1)),
                new Cell(true, new Coordinates(0, 1)),
                new Cell(true, new Coordinates(1, 0)),
                new Cell(true, new Coordinates(1, 1)),
                new Cell(true, new Coordinates(1, -1)),
                new Cell(true, new Coordinates(-1, -1)),
                new Cell(true, new Coordinates(-1, 0)),
                new Cell(true, new Coordinates(-1, 1)),
                cell
        ));

        // [Act]
        cell.evolve(neighborhood);

        // [Assert]
        assertThat(neighborhood).doesNotContain(cell);

    }

}