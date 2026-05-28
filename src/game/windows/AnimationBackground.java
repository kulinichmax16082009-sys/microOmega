package game.windows;

import game.gameObjects.MovingCell;
import game.gameUtils.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is responsible for animation in background of main menu.
 *
 * @author Maksym Kulynych
 */
public class AnimationBackground extends JPanel {
    private final ArrayList<MovingCell> movingCells;
    private final RandomGenerator rnd;

    private static final int MOVING_CELLS_COUNT = 100;

    public AnimationBackground() {
        this.rnd = new RandomGenerator();
        this.movingCells = new ArrayList<>();

        Timer timer = new Timer(16, e -> {
            updateCells();
            repaint();
        });

        timer.start();
    }

    /**
     * This method initializes the moving cells list with random values and positions.
     */
    private void initCells() {
        for (int i = 0; i < MOVING_CELLS_COUNT; i++) {
            MovingCell movingCell = new MovingCell((int)Math.pow(2, rnd.randomNumber(2, 11)));

            movingCell.setSize(rnd.randomNumber(50, 90));

            movingCell.setX(rnd.randomNumber(movingCell.getSize(), getWidth()) - movingCell.getSize());
            movingCell.setY(rnd.randomNumber(movingCell.getSize(), getHeight()) - movingCell.getSize());

            int dx = rnd.randomNumber(-2, 2);
            int dy = rnd.randomNumber(-2, 2);

            if (dx == 0) dx = 1;
            if (dy == 0) dy = 1;

            movingCell.setDeltaX(dx);
            movingCell.setDeltaY(dy);

            movingCells.add(movingCell);
        }
    }

    /** This method updates the position of each moving cell and checks for collisions with the panel borders.
     * If a cell collides with a border, its direction is reversed.
     */
    private void updateCells() {
        for (MovingCell movingCell : movingCells) {

            movingCell.setX(movingCell.getX() + movingCell.getDeltaX());
            movingCell.setY(movingCell.getY() + movingCell.getDeltaY());

            if (movingCell.getX() < 0 || movingCell.getX() > getWidth() - movingCell.getSize()) {
                movingCell.setDeltaX(-movingCell.getDeltaX());
            }

            if (movingCell.getY() < 0 || movingCell.getY() > getHeight() - movingCell.getSize()) {
                movingCell.setDeltaY(-movingCell.getDeltaY());
            }
        }
    }

    /**
     * This method paints the background and the moving cells on the panel.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (movingCells.isEmpty()) initCells();

        g2d.setColor(new Color(252, 220, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        updateCells();

        for (MovingCell movingCell : movingCells) {
            movingCell.paint(g2d, movingCell.getX(),
                    movingCell.getY(), movingCell.getSize(),
                    15);
        }
    }
}
