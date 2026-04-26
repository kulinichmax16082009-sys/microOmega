import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IntroducingWindow {

    private final JFrame frame;

    //TODO: json file for settings
    private ArrayList<JButton> buttons;
    private final static int WINDOW_SIZE = 400;
    private final static int BUTTON_WIDTH = 200;
    private final static int BUTTON_HEIGHT = 50;

    public IntroducingWindow() {
        frame = new JFrame("2048 - Introducing Window");

        //Frame settings
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        initButtons();
        setButtonsLocation();

        //Final settings
        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(false);
    }

    public void initButtons() {
        buttons = new ArrayList<>();

        JPanel buttonPanel = new JPanel(null);

        buttonPanel.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));

        JButton start = new JButton("START");
        JButton load = new JButton("LOAD");
        JButton quit = new JButton("QUIT");

        buttons.add(start);
        buttons.add(load);
        buttons.add(quit);

        for (JButton button : buttons) {
            button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        start.addActionListener(e -> {
            new GameWindow();
            frame.dispose();
        });

        quit.addActionListener(e -> System.exit(0));

        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    public void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation((WINDOW_SIZE - BUTTON_WIDTH) / 2,
                    (WINDOW_SIZE - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (int)(1.5 * BUTTON_HEIGHT));
        }
    }

    //TODO: add some abstract class for windows and move this method there
    public void show() {
        frame.setVisible(true);
    }

    public void showNextWindow() {
        frame.dispose();
    }
}
