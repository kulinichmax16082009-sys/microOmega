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
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        boolean moved = false;

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            moved = boardManager.moveCellsLeft(player, gameWindow);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            moved = boardManager.moveCellsRight(player, gameWindow);
        } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            moved = boardManager.moveCellsUp(player, gameWindow);
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            moved = boardManager.moveCellsDown(player, gameWindow);
        }

        if (moved) {
            boardManager.addRandomCell(new RandomGenerator());
        }
    }
}
