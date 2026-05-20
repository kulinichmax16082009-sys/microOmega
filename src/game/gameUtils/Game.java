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
    private EndWindow endWindow;
    private Timer timer;
    private GameData gameData;

    private BoardManager boardManager;
    private Player player;

    public Game() {
        this.player = new Player();
        this.boardManager = new BoardManager(GameWindow.CELLS_COUNT);
        this.gameData = new GameData(player, boardManager);
    }

    public void play() {
        introducingWindow = new IntroducingWindow(this);
    }

    @Override
    public void onStart(boolean isLoading) {
        if (isLoading) {
            gameData = GameData.loadData("resources/lastSave/save.dat");
            if (gameData == null) {
                introducingWindow.showErrorMessage("No saved game found");
                gameData = new GameData(player, boardManager);
                return;
            } else {
                this.player = gameData.getPlayer();
                this.boardManager = gameData.getBoardManager();
            }
        } else {
            boardManager.addRandomCell(new RandomGenerator());
            boardManager.addRandomCell(new RandomGenerator());
        }

        introducingWindow.close();

        GamePanel gamePanel = new GamePanel(boardManager);
        this.gameWindow = new GameWindow(gamePanel, gameData);

        gameWindow.updateScoreLabel(player.getScore());
        gameWindow.updateTimeLabel(player.getTime());

        MyKeyAdapter keyAdapter = new MyKeyAdapter(boardManager, player, gameWindow, gameData);
        gamePanel.addKeyAdapter(keyAdapter);

        startTimer(1000);
    }

    public void gameOver() {
        boolean badEnd = boardManager.isFull() && !boardManager.isThere2048();
        boolean goodEnd = boardManager.isThere2048();

        if (goodEnd || badEnd) {
            endWindow = new EndWindow(player);
            stopTimer();
            gameWindow.close();
        }

        if (badEnd) endWindow.initBadEnd();
        else if (goodEnd) endWindow.initGoodEnd();
    }

    public void startTimer(int delay) {
        timer = new Timer(delay, e -> {
            player.tickTime();
            gameWindow.updateTimeLabel(player.getTime());
            gameData.update(player, boardManager);
            gameOver();
        });

        timer.start();
    }

    public void stopTimer() {
        if (timer != null) timer.stop();
    }
}
