import javax.swing.*;
import java.awt.*;

public class BasicWindow {
    protected JFrame frame;
    protected BasicWindow nextWindow;
    protected int windowSize;

    public BasicWindow(String title, int windowSize, BasicWindow nextWindow) {
        this.windowSize = windowSize;
        this.nextWindow = nextWindow;

        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    //TODO: handle next window exceptions like null pointer exception
    public void showNextWindow() {
        hide();
        nextWindow.show();
    }
}
