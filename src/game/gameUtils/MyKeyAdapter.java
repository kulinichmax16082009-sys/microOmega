package game.gameUtils;

import game.gameObjects.Player;
import game.windows.GameWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    private final BoardManager boardManager;
    private final Player player;
    private final GameWindow gameWindow;

    public MyKeyAdapter(BoardManager boardManager, Player player, GameWindow gameWindow) {
        this.boardManager = boardManager;
        this.player = player;
        this.gameWindow = gameWindow;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            if (boardManager.moveCellsLeft(player, gameWindow)) boardManager.addRandomCell(new RandomGenerator());
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            if (boardManager.moveCellsRight(player, gameWindow)) boardManager.addRandomCell(new RandomGenerator());
        } else if (e.getKeyCode() == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            if (boardManager.moveCellsUp(player, gameWindow)) boardManager.addRandomCell(new RandomGenerator());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if (boardManager.moveCellsDown(player, gameWindow)) boardManager.addRandomCell(new RandomGenerator());
        }
    }
}
