package game.gameUtils;

import game.gameObjects.Player;
import game.windows.EndWindow;
import game.windows.GamePanel;
import game.windows.GameWindow;
import game.windows.IntroducingWindow;

import javax.swing.*;

/**
 * This class represents the main game logic. It manages the game windows, player, board manager, and timer.
 *
 * @author Maksym Kulynych
 */
public class Game implements StartListener {
    private IntroducingWindow introducingWindow;
    private GameWindow gameWindow;
    private EndWindow endWindow;
    private Timer timer;
    private GameData gameData;
    private final FileManager fileManager;

    private BoardManager boardManager;
    private Player player;

    public static final String SAVE_LOAD_PATH = "resources/save.dat";
    public static final String STATS_SAVE_PATH = "resources/txtFiles/stats.txt";

    public Game() {
        this.player = new Player();
        this.boardManager = new BoardManager(GameWindow.CELLS_COUNT);
        this.gameData = new GameData(player, boardManager);
        this.fileManager = new FileManager();
    }

    /**
     * This method starts the game by showing the introducing window.
     */
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

    /**
     * This method checks if the game is over and shows the end window with the message based on the result.
     */
    private void gameOver() {
        boolean badEnd = boardManager.isFull() && !boardManager.isThere2048();
        boolean goodEnd = boardManager.isThere2048();

        if (goodEnd || badEnd) {
            endWindow = new EndWindow(player);
            stopTimer();
            fileManager.saveStats(player, STATS_SAVE_PATH);
            gameWindow.close();
        }

        if (badEnd) endWindow.initBadEnd();
        else if (goodEnd) endWindow.initGoodEnd();
    }

    /**
     * This method starts the game timer which updates the player's time and the time label in the game window every second.
     * It also checks for game over conditions after each tick.
     */
    private void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.updateTimeLabel(player.getTime());
            gameData.update(player, boardManager);
            gameOver();
        });

        timer.start();
    }

    /**
     * This method stops the game timer.
     */
    private void stopTimer() {
        if (timer != null) timer.stop();
    }

    /**
     * This method initializes the game UI by creating a game panel and a game window, and setting up the key adapter.
     */
    private void initGameUI() {
        GamePanel gamePanel = new GamePanel(boardManager);
        this.gameWindow = new GameWindow(gamePanel, gameData);

        gameWindow.updateScoreLabel(player.getScore());
        gameWindow.updateTimeLabel(player.getTime());

        MyKeyAdapter keyAdapter = new MyKeyAdapter(boardManager, player, gameWindow, gameData);
        gamePanel.addKeyAdapter(keyAdapter);
    }

    /**
     * This method checks if loading the game data was successful by trying to load the data and checking if it is not null.
     * @return true if loading was successful, false otherwise
     */
    private boolean isLoadingSuccessful() {
        GameData loadedData = GameData.loadData(SAVE_LOAD_PATH);
        return loadedData != null;
    }

    /**
     * This method adds two random cells on random positions to the board at the start of the game.
     */
    private void addStartingCells() {
        boardManager.addRandomCell(new RandomGenerator());
        boardManager.addRandomCell(new RandomGenerator());
    }

    /**
     * This method loads the game data from the save file and updates the player and board manager based on loaded data.
     */
    private void loadGame() {
        this.gameData = GameData.loadData(SAVE_LOAD_PATH);
        this.player = gameData.getPlayer();
        this.boardManager = gameData.getBoardManager();
    }
}
