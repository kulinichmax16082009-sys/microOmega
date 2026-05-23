package game.windows;

import game.gameUtils.Game;
import game.gameUtils.GameData;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends BasicWindow {
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JButton saveButton;
    private JButton quitButton;

    private final GamePanel gamePanel;

    public final static int CELL_SIZE = 100;
    public final static int CELLS_COUNT = 4;
    public final static int GAP_SIZE = 12;

    public GameWindow(GamePanel gamePanel, GameData gameData) {
        super("2048 - Game", CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1), CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1));
        this.gamePanel = gamePanel;
        this.gamePanel.setPreferredSize(new Dimension(width, height));

        //Initialize components
        intiScoreLabel();
        initTimeLabel();

        //Initialize buttons
        initSaveButton(gameData);
        initQuitButton();

        //Add components to window
        addButtonsToWindow();
        addLabelsToWindow();

        frame.add(this.gamePanel);

        this.gamePanel.requestFocusInWindow();

        //Final settings
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void addLabelsToWindow() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        labelPanel.setBackground(new Color(195, 196, 195));
        labelPanel.add(scoreLabel);
        labelPanel.add(timeLabel);

        frame.add(labelPanel, BorderLayout.NORTH);
    }

    private void addButtonsToWindow() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(195, 196, 195));
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void intiScoreLabel() {
        scoreLabel = new JLabel("Score: 0 ", JLabel.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(195, 196, 195));
    }

    private void initTimeLabel() {
        timeLabel = new JLabel("| Time: 0 s", JLabel.CENTER);
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(195, 196, 195));
    }

    private void initSaveButton(GameData gameData) {
        saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            gameData.saveGame(Game.SAVE_LOAD_PATH);
            gamePanel.requestFocusInWindow();
        });

        saveButton.setBackground(new Color(150, 150, 150));
    }

    private void initQuitButton() {
        quitButton = new JButton("Quit");

        quitButton.addActionListener(e -> System.exit(0));

        quitButton.setBackground(new Color(150, 150, 150));
    }

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
