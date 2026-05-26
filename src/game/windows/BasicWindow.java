package game.windows;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a basic window in the game, providing common functionality for all windows.
 *
 * @author Maksym Kulynych
 */
public abstract class BasicWindow {
    protected JFrame frame;
    protected int width;
    protected int height;

    /**
     * This constructor initializes the basic window with the specified title, width and height and basic settings for the frame.
     *
     * @param title the title of the window
     * @param width the width of the window
     * @param height the height of the window
     */
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

    /**
     * This method closes the window by disposing of the frame.
     */
    public void close() {
        frame.dispose();
    }

    /**
     * This method returns the path to the image used as the window's icon.
     *
     * @return the path to the image file
     */
    public abstract String getImagePath();
}
