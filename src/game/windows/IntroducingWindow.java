package game.windows;

import game.gameUtils.StartListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IntroducingWindow extends BasicWindow {

    //TODO: json file for settings
    private ArrayList<JButton> buttons;
    private final StartListener startListener;
    private JPanel introducingPanel;

    private final static int BUTTON_WIDTH = 300;
    private final static int BUTTON_HEIGHT = 70;
    private final static float BUTTON_DISTANCE_FACTOR = 1.5f;

    private final static int Y_OFFSET = 110;

    public IntroducingWindow(StartListener startListener) {
        super("2048 - Introducing Window", 450, 600);
        this.startListener = startListener;
        initIntroducingPanel();

        initTitleLabel();
        initButtons();
        setButtonsLocation();
        setBackgroundImage();

        //Final settings
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void initButtons() {
        buttons = new ArrayList<>();

        JButton start = new JButton();
        start.setIcon(new ImageIcon("resources/buttonIcons/startButtonIcon.png"));

        JButton load = new JButton();
        load.setIcon(new ImageIcon("resources/buttonIcons/loadButtonIcon.png"));

        JButton quit = new JButton();
        quit.setIcon(new ImageIcon("resources/buttonIcons/quitButtonIcon.png"));

        buttons.add(start);
        buttons.add(load);
        buttons.add(quit);

        start.addActionListener(e -> startListener.onStart(false));

        load.addActionListener(e -> startListener.onStart(true));

        quit.addActionListener(e -> System.exit(0));

        for (JButton button : buttons) {
            button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        for (JButton button : buttons) {
            introducingPanel.add(button);
        }
    }

    public void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation((width - BUTTON_WIDTH) / 2,
                    (height - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (int) (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT) + Y_OFFSET);
        }
    }

    public void initTitleLabel() {
        ImageIcon icon = new ImageIcon("resources/labelIcons/2048Label.png");

        JLabel titleLabel = new JLabel(icon, JLabel.CENTER);

        titleLabel.setBounds((width - icon.getIconWidth()) / 2, 10, icon.getIconWidth(), icon.getIconHeight());

        introducingPanel.add(titleLabel);
    }

    public void setBackgroundImage() {
        ImageIcon background = new ImageIcon("resources/backgrounds/introducingWindowBackground.png");

        JLabel backgroundLabel = new JLabel(background);

        backgroundLabel.setBounds(0, 0, width, height);

        introducingPanel.add(backgroundLabel);

        introducingPanel.setComponentZOrder(backgroundLabel, introducingPanel.getComponentCount() - 1);
    }

    public void initIntroducingPanel() {
        introducingPanel = new JPanel(null);

        introducingPanel.setOpaque(false);

        introducingPanel.setPreferredSize(new Dimension(width, height));

        frame.add(introducingPanel, BorderLayout.CENTER);
    }

    @Override
    public String getImagePath() {
        return "resources/windowIcons/introducingWindowIcon.png";
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
