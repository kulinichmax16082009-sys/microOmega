import javax.swing.*;
import java.awt.*;

public class GameWindow extends BasicWindow {

    private JLabel scoreLabel;
    private final GamePanel gamePanel;

    public final static int CELL_SIZE = 100;
    public final static int CELLS_COUNT = 4;
    public final static int GAP_SIZE = 12;

    public GameWindow() {
        super("2048 - Game", CELL_SIZE * CELLS_COUNT + GAP_SIZE * (CELLS_COUNT + 1), "");
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(windowSize, windowSize));

        intiScoreLabel();

        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(gamePanel);

        //Final settings
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void intiScoreLabel() {
        scoreLabel = new JLabel("Score: 0 | Time: 0 s", JLabel.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(195, 196, 195));
    }

    public void updateLabel(int score, long time) {
        if (time >= 60) {
            long minutes = 0;
            long seconds = time;

            while (seconds >= 60) {
                seconds -= 60;
                minutes++;
            }
            scoreLabel.setText("Score: " + score + " | Time: " + minutes + " min " + seconds + " s");

        } else {
            scoreLabel.setText("Score: " + score + " | Time: " + time + " s");
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
