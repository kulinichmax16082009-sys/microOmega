package game.windows;

import game.gameUtils.BoardManager;
import game.gameUtils.MyKeyAdapter;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final BoardManager boardManager;

    public GamePanel(BoardManager boardManager) {
        this.boardManager = boardManager;
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        setBackground(new Color(153, 135, 119));

        for (int i = 0; i < boardManager.getBoard().length; i++) {
            for (int j = 0; j < boardManager.getBoard()[i].length; j++) {
                int x = GameWindow.CELL_SIZE * j + (GameWindow.GAP_SIZE) * j + GameWindow.GAP_SIZE;
                int y = GameWindow.CELL_SIZE * i + (GameWindow.GAP_SIZE) * i + GameWindow.GAP_SIZE;

                g2d.setColor(boardManager.getBoard()[i][j].getColor());
                g2d.fillRoundRect(x, y, GameWindow.CELL_SIZE, GameWindow.CELL_SIZE, 15, 15);

                g2d.setColor(boardManager.getBoard()[i][j].getValue() <= 4 ? new Color(0x776e65) : Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 38));
                if (boardManager.getBoard()[i][j].getValue() != 0) {
                    String s = String.valueOf(boardManager.getBoard()[i][j].getValue());
                    FontMetrics fm = g2d.getFontMetrics();
                    int strWidth = fm.stringWidth(s);
                    int strHeight = -(int) fm.getLineMetrics(s, g2d).getBaselineOffsets()[2];
                    g2d.drawString(s, x + (GameWindow.CELL_SIZE - strWidth) / 2, y + GameWindow.CELL_SIZE - (GameWindow.CELL_SIZE - strHeight) / 2 - 5);
                }
            }
        }
    }

    public void addKeyAdapter(MyKeyAdapter keyAdapter) {
        addKeyListener(keyAdapter);
    }
}
