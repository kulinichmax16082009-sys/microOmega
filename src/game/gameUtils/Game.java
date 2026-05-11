package game.gameUtils;

import game.gameObjects.Player;
import game.windows.GamePanel;
import game.windows.GameWindow;
import game.windows.IntroducingWindow;

import javax.swing.*;

public class Game implements StartListener {
    private IntroducingWindow introducingWindow;
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private BoardManager boardManager;
    private Player player;

    public Game() {
        this.player = new Player();
        this.boardManager = new BoardManager(GameWindow.CELLS_COUNT);
        this.boardManager.initBoard();
        this.gamePanel = new GamePanel(boardManager);
    }

    public void play() {
        introducingWindow = new IntroducingWindow(this);
    }

    @Override
    public void onStart() {
        introducingWindow.close();

        boardManager.addRandomCell(new RandomGenerator());
        boardManager.addRandomCell(new RandomGenerator());

        gameWindow = new GameWindow(gamePanel);

        startTimer();

        //You can add some code here
    }

    public void startTimer() {
        Timer timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.updateLabel(player.getScore(), player.getTime());
            gamePanel.repaint();
        });

        timer.start();
    }
}
