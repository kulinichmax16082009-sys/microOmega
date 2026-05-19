package game.gameUtils;

import game.gameObjects.Player;
import game.windows.EndWindow;
import game.windows.GamePanel;
import game.windows.GameWindow;
import game.windows.IntroducingWindow;

import javax.swing.*;

public class Game implements StartListener {
    private IntroducingWindow introducingWindow;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private EndWindow endWindow;
    private Timer timer;
    private GameData gameData;

    private BoardManager boardManager;
    private Player player;

    public Game() {
        this.player = new Player();
        this.boardManager = new BoardManager(GameWindow.CELLS_COUNT);
        this.boardManager.initBoard();
        this.gameData = new GameData(player, boardManager);
    }

    public void play() {
        introducingWindow = new IntroducingWindow(this);
    }

    @Override
    public void onStart(boolean isLoading) {
        if (isLoading) {
            this.gameData = GameData.loadGame("resources/lastSave/save.dat");
            this.player = gameData.getPlayer();
            this.boardManager = gameData.getBoardManager();
        } else {
            boardManager.addRandomCell(new RandomGenerator());
            boardManager.addRandomCell(new RandomGenerator());
        }

        introducingWindow.close();

        this.gamePanel = new GamePanel(boardManager);
        this.gameWindow = new GameWindow(gamePanel, gameData);

        gameWindow.updateScoreLabel(player.getScore());
        gameWindow.updateTimeLabel(player.getTime());

        MyKeyAdapter keyAdapter = new MyKeyAdapter(boardManager, player, gameWindow);
        gamePanel.addKeyAdapter(keyAdapter);

        startTimer();
    }

    public void checkGameOver() {
        if (boardManager.isFull() && !boardManager.isThere2048()) {
            endWindow = new EndWindow(player);
            stopTimer();
            gameWindow.close();
            endWindow.initBadEnd();
        } else if (boardManager.isThere2048()) {
            endWindow = new EndWindow(player);
            stopTimer();
            gameWindow.close();
            endWindow.initGoodEnd();
        }
    }

    public void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.updateTimeLabel(player.getTime());
            gameData.update(player, boardManager);
            checkGameOver();
        });

        timer.start();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
}
