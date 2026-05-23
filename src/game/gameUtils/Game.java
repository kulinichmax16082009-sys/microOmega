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

    public static final String SAVE_LOAD_PATH = "resources/lastSave/save.dat";

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
            if (!isLoadingSuccessful()) {
                introducingWindow.showErrorMessage("No saved game found");
                gameData = new GameData(player, boardManager);
                return;
            } else loadGame();
        } else addStartingCells();

        introducingWindow.close();

        initGameUI();

        startTimer();
    }

    private void gameOver() {
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

    private void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.updateTimeLabel(player.getTime());
            gameData.update(player, boardManager);
            gameOver();
        });

        timer.start();
    }

    private void stopTimer() {
        if (timer != null) timer.stop();
    }

    private void initGameUI() {
        GamePanel gamePanel = new GamePanel(boardManager);
        this.gameWindow = new GameWindow(gamePanel, gameData);

        gameWindow.updateScoreLabel(player.getScore());
        gameWindow.updateTimeLabel(player.getTime());

        MyKeyAdapter keyAdapter = new MyKeyAdapter(boardManager, player, gameWindow, gameData);
        gamePanel.addKeyAdapter(keyAdapter);
    }

    private boolean isLoadingSuccessful() {
        GameData loadedData = GameData.loadData(SAVE_LOAD_PATH);
        return loadedData != null;
    }

    private void addStartingCells() {
        boardManager.addRandomCell(new RandomGenerator());
        boardManager.addRandomCell(new RandomGenerator());
    }

    private void loadGame() {
        this.gameData = GameData.loadData(SAVE_LOAD_PATH);
        this.player = gameData.getPlayer();
        this.boardManager = gameData.getBoardManager();
    }
}
