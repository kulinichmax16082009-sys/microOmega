package game.windows;

import game.gameUtils.GameData;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends BasicWindow {

    private JLabel scoreLabel;
    private JLabel timeLabel;

    public final static int CELL_SIZE = 100;
    public final static int CELLS_COUNT = 4;
    public final static int GAP_SIZE = 12;

    public GameWindow(GamePanel gamePanel, GameData gameData) {
        super("2048 - Game", CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1), CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1));
        gamePanel.setPreferredSize(new Dimension(width, height));

        intiScoreLabel();
        initTimeLabel();
        initSaveButton(gameData, gamePanel);

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        labelPanel.setBackground(new Color(195, 196, 195));
        labelPanel.add(scoreLabel);
        labelPanel.add(timeLabel);

        frame.add(labelPanel, BorderLayout.NORTH);
        frame.add(gamePanel);

        gamePanel.requestFocusInWindow();

        //Final settings
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void intiScoreLabel() {
        scoreLabel = new JLabel("Score: 0 ", JLabel.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(195, 196, 195));
    }

    public void initTimeLabel() {
        timeLabel = new JLabel("| Time: 0 s", JLabel.CENTER);
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(195, 196, 195));
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

    public void initSaveButton(GameData gameData, GamePanel gamePanel) {
        JButton saveButton = new JButton("Save");
        JPanel buttonPanel = new JPanel();

        saveButton.addActionListener(e -> {
            gameData.saveGame("resources/lastSave/save.dat");

            gamePanel.requestFocusInWindow();
        });

        saveButton.setBackground(new Color(150, 150, 150));
        buttonPanel.add(saveButton);
        buttonPanel.setBackground(new Color(195, 196, 195));

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public String getImagePath() {
        return "resources/windowIcons/gameWindowIcon.png";
    }
}
