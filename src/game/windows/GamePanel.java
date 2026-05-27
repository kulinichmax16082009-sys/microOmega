package game.windows;

import game.gameUtils.BoardManager;
import game.gameUtils.MyKeyAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the main game panel where the game board is painted and user inputs are handled.
 *
 * @author Maksym Kulynych
 */
public class GamePanel extends JPanel {
    private final BoardManager boardManager;

    /**
     * This constructor initializes the game panel with the board manager and sets it to be focusable for key events.
     *
     * @param boardManager the board manager that is used to paint current board state
     */
    public GamePanel(BoardManager boardManager) {
        this.boardManager = boardManager;
        setFocusable(true);
    }

    /**
     * This method is for painting the game board to the panel. It iterates the board cells and paints them with their colors and values.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        setBackground(new Color(153, 135, 119));

        for (int i = 0; i < boardManager.getBoard().length; i++) {
            for (int j = 0; j < boardManager.getBoard()[i].length; j++) {
                int x = GameWindow.CELL_SIZE * j + (GameWindow.GAP_SIZE) * j + GameWindow.GAP_SIZE;
                int y = GameWindow.CELL_SIZE * i + (GameWindow.GAP_SIZE) * i + GameWindow.GAP_SIZE;

                boardManager.getBoard()[i][j].paint(g2d, x, y, GameWindow.CELL_SIZE, 15);
            }
        }
    }

    /**
     * This method adds a key adapter to the panel to handle user inputs for moving the cells on the board.
     *
     * @param keyAdapter the key adapter that will be added to the panel
     */
    public void addKeyAdapter(MyKeyAdapter keyAdapter) {
        addKeyListener(keyAdapter);
    }
}
