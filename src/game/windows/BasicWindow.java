package game.windows;

import javax.swing.*;
import java.awt.*;

public class BasicWindow {
    protected JFrame frame;
    protected int windowSize;

    public BasicWindow(String title, int windowSize, String imagePath) {
        this.windowSize = windowSize;

        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(imagePath).getImage());
        frame.setFocusable(true);
    }

    public void close() {
        frame.dispose();
    }
}
