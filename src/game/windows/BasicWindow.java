package game.windows;

import javax.swing.*;
import java.awt.*;

public abstract class BasicWindow {
    protected JFrame frame;
    protected int width;
    protected int height;

    public BasicWindow(String title, int width, int height) {
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getImagePath()).getImage());
        frame.setFocusable(true);
    }

    public void close() {
        frame.dispose();
    }

    public abstract String getImagePath();
}
