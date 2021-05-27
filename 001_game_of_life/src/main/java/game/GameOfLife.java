package game;

import game.graphic.Grid;

public class GameOfLife {

    private final Grid grid;

    public GameOfLife() {
        this.grid = new Grid();
    }

    public static void main(final String[] arguments) {
        final GameOfLife game = new GameOfLife();
        game.start();
    }

    private void start() {
        this.grid.display();
    }

}