package game.gameUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    private BoardManager boardManager;

    public MyKeyAdapter(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            if (boardManager.moveCellsLeft()) boardManager.addRandomCell(new RandomGenerator());
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            if (boardManager.moveCellsRight()) boardManager.addRandomCell(new RandomGenerator());
        } else if (e.getKeyCode() == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            if (boardManager.moveCellsUp()) boardManager.addRandomCell(new RandomGenerator());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if (boardManager.moveCellsDown()) boardManager.addRandomCell(new RandomGenerator());
        }
    }
}
