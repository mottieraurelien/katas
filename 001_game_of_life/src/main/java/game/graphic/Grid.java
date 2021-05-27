package game.graphic;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.WHITE;

public class Grid {

    private static final String LABEL = "Game of Life";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private final Frame frame;
    private final Canvas canvas;

    public Grid() {
        this.frame = new JFrame(LABEL);
        this.canvas = new Canvas();
        this.canvas.setSize(WIDTH, HEIGHT);
        this.canvas.setBackground(WHITE);
        this.frame.add(this.canvas);
        this.frame.pack();
    }

    public void display() {

        this.frame.setVisible(true);

        final Graphics graphics = new DebugGraphics();
        this.canvas.print(graphics);

        graphics.fillRect(1, 1, 10, 10);
        this.canvas.update(graphics);

    }

    public void refresh(){

        // TODO Re-render the grid from the new data.

    }

}
