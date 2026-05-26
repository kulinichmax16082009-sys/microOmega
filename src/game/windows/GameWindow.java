package game.windows;

import game.gameUtils.Game;
import game.gameUtils.GameData;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the main game window, which displays the game board, score, time and quit, save buttons.
 *
 * @author Maksym Kulynych
 */
public class GameWindow extends BasicWindow {
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JButton saveButton;
    private JButton quitButton;

    private final GamePanel gamePanel;

    public final static int CELL_SIZE = 100;
    public final static int CELLS_COUNT = 4;
    public final static int GAP_SIZE = 12;

    /**
     * This constructor initializes the game window with the specified game panel and game data.
     * Also score and time labels and save and quit buttons are initialized and added to the window.
     *
     * @param gamePanel the panel that displays the game board
     * @param gameData the data that is used in the save button action listener to save the game state
     */
    public GameWindow(GamePanel gamePanel, GameData gameData) {
        super("2048 - Game", CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1), CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1));
        this.gamePanel = gamePanel;
        this.gamePanel.setPreferredSize(new Dimension(width, height));

        intiScoreLabel();
        initTimeLabel();

        initSaveButton(gameData);
        initQuitButton();

        addButtonsToWindow();
        addLabelsToWindow();

        frame.add(this.gamePanel);

        this.gamePanel.requestFocusInWindow();

        //Final settings
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method creates a panel for score and time labels, sets its background color and adds the labels to it.
     */
    private void addLabelsToWindow() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        labelPanel.setBackground(new Color(195, 196, 195));
        labelPanel.add(scoreLabel);
        labelPanel.add(timeLabel);

        frame.add(labelPanel, BorderLayout.NORTH);
    }

    /**
     * This method creates a panel for save and quit buttons, sets its background color and adds the buttons to it.
     */
    private void addButtonsToWindow() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(195, 196, 195));
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * This method initializes the score label, sets its text, position, background color and makes it opaque.
     */
    private void intiScoreLabel() {
        scoreLabel = new JLabel("Score: 0 ", JLabel.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(195, 196, 195));
    }
    /**
     * This method initializes the time label, sets its text, position, background color and makes it opaque.
     */
    private void initTimeLabel() {
        timeLabel = new JLabel("| Time: 0 s", JLabel.CENTER);
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(195, 196, 195));
    }

    /**
     * This method initializes the save button, sets its text, background color and adds an action listener.
     *
     * @param gameData the data that is used in the action listener to save the game state
     */
    private void initSaveButton(GameData gameData) {
        saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            gameData.saveGame(Game.SAVE_LOAD_PATH);
            gamePanel.requestFocusInWindow();
        });

        saveButton.setBackground(new Color(150, 150, 150));
    }

    /**
     * This method initializes the quit button, sets its text, background color and adds an action listener.
     */
    private void initQuitButton() {
        quitButton = new JButton("Quit");

        quitButton.addActionListener(e -> System.exit(0));

        quitButton.setBackground(new Color(150, 150, 150));
    }

    /**
     * This method updates the time label with the given time in seconds, converting it to minutes and seconds if necessary.
     *
     * @param time the time in seconds to be displayed on the label
     */
    public void updateTimeLabel(long time) {
        if (time >= 60) {
            long minutes = 0;
            long seconds = time;

            while (seconds >= 60) {
                seconds -= 60;
                minutes++;
            }
            timeLabel.setText("| Time: " + minutes + " min " + seconds + " s");

        } else {
            timeLabel.setText("| Time: " + time + " s");
        }
    }

    /**
     * This method updates the score label with the given score.
     *
     * @param score the score to be displayed on the label
     */
    public void updateScoreLabel(int score) {
        scoreLabel.setText("Score: " + score + " ");
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public String getImagePath() {
        return "resources/windowIcons/gameWindowIcon.png";
    }
}
