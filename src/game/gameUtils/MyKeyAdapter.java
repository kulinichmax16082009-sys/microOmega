package game.gameUtils;

import game.gameObjects.Player;
import game.windows.GameWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class is used for processing key events in game.
 *
 * @author Maksym Kulynych
 */
public class MyKeyAdapter extends KeyAdapter {
    private final BoardManager boardManager;
    private final Player player;
    private final GameWindow gameWindow;
    private final GameData gameData;

    public MyKeyAdapter(BoardManager boardManager, Player player, GameWindow gameWindow, GameData gameData) {
        this.boardManager = boardManager;
        this.player = player;
        this.gameWindow = gameWindow;
        this.gameData = gameData;
    }

    /**
     * This method processes key events for saving the game when Ctrl + S is pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            gameData.saveGame(Game.SAVE_LOAD_PATH);
        }
    }

    /**
     * This method processes key events for moving cells on the board.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        boolean moved = false;

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            moved = boardManager.moveCellsLeft(player);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            moved = boardManager.moveCellsRight(player);
        } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            moved = boardManager.moveCellsUp(player);
        } else if ((keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) && !e.isControlDown()) {
            moved = boardManager.moveCellsDown(player);
        }

        if (moved) {
            boardManager.addRandomCell(new RandomGenerator());
            gameWindow.updateScoreLabel(player.getScore());
            gameWindow.getGamePanel().repaint();
        }
    }
}
