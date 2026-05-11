package game.windows;

import game.gameUtils.StartListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IntroducingWindow extends BasicWindow {

    //TODO: json file for settings
    private ArrayList<JButton> buttons;
    private StartListener startListener;

    private final static int BUTTON_WIDTH = 200;
    private final static int BUTTON_HEIGHT = 50;
    private final static float BUTTON_DISTANCE_FACTOR = 1.5f;

    public IntroducingWindow(StartListener startListener) {
        super("2048 - Introducing Window", 400,"resources/icons/introducingWindowIcon.png");
        this.startListener = startListener;

        initTitleLabel();
        initButtons();
        setButtonsLocation();

        //Final settings
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void initButtons() {
        buttons = new ArrayList<>();

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setPreferredSize(new Dimension(windowSize, windowSize));

        JButton start = new JButton("START");
        JButton load = new JButton("LOAD");
        JButton quit = new JButton("QUIT");

        buttons.add(start);
        buttons.add(load);
        buttons.add(quit);

        start.addActionListener(e -> {
            startListener.onStart();
        });

        //TODO: implement load button
        load.addActionListener(e -> System.out.println("Load button clicked"));

        quit.addActionListener(e -> System.exit(0));

        for (JButton button : buttons) {
            button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    public void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation((windowSize - BUTTON_WIDTH) / 2,
                    (windowSize - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (int) (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT));
        }
    }

    public void initTitleLabel() {
        JLabel titleLabel = new JLabel("2048", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(195, 196, 195));
        frame.add(titleLabel, BorderLayout.NORTH);
    }
}
