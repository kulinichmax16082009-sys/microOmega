import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private BoardManager boardManager;

    public GamePanel() {
        boardManager = new BoardManager(GameWindow.CELLS_COUNT);
        boardManager.initBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(153, 135, 119));

        for (int i = 0; i < boardManager.getBoard().length; i++) {
            for (int j = 0; j < boardManager.getBoard()[i].length; j++) {
                int x = GameWindow.CELL_SIZE * j + (GameWindow.GAP_SIZE) * j + GameWindow.GAP_SIZE;
                int y = GameWindow.CELL_SIZE * i + (GameWindow.GAP_SIZE) * i + GameWindow.GAP_SIZE;

                g.setColor(boardManager.getBoard()[i][j].getColor());
                g.fillRoundRect(x, y, GameWindow.CELL_SIZE, GameWindow.CELL_SIZE, 15, 15);

                g.setColor(boardManager.getBoard()[i][j].getValue() <= 4 ? new Color(0x776e65) : Color.WHITE);
                g.setFont(new Font("Arial Bold", Font.BOLD, 38));
                if (boardManager.getBoard()[i][j].getValue() != 0) {
                    String s = String.valueOf(boardManager.getBoard()[i][j].getValue());
                    FontMetrics fm = g.getFontMetrics();
                    int strWidth = fm.stringWidth(s);
                    int strHeight = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
                    g.drawString(s, x + (GameWindow.CELL_SIZE - strWidth) / 2, y + GameWindow.CELL_SIZE - (GameWindow.CELL_SIZE - strHeight) / 2 - 5);
                }
            }
        }

        repaint();
    }
}
