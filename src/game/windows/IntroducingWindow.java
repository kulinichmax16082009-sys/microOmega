package game.windows;

import game.gameUtils.StartListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the introducing window of the game, which is the first window that appears when the game is launched.
 * It has options to start a new game, load a saved game, or quit the application.
 *
 * @author Maksym Kulynych
 */
public class IntroducingWindow extends BasicWindow {
    private ArrayList<JButton> buttons;
    private final StartListener startListener;
    private JPanel introducingPanel;

    private final static int BUTTON_WIDTH = 300;
    private final static int BUTTON_HEIGHT = 70;
    private final static float BUTTON_DISTANCE_FACTOR = 1.5f;

    private final static int Y_OFFSET = 110;

    private final static String START_BUTTON_PATH = "resources/buttonIcons/startButtonIcon.png";
    private final static String LOAD_BUTTON_PATH = "resources/buttonIcons/loadButtonIcon.png";
    private final static String QUIT_BUTTON_PATH = "resources/buttonIcons/quitButtonIcon.png";

    private final static String TITLE_LABEL_PATH = "resources/labelIcons/2048Label.png";
    private final static String BACKGROUND_IMAGE_PATH = "resources/backgrounds/introducingWindowBackground.png";

    /**
     * This constructor initializes the introducing window with the specified start listener.
     *
     * @param startListener the listener that will handle the start game actions
     */
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

    /**
     * This method initializes the buttons for starting a new game, loading a saved game, and quitting the application.
     * It sets their icons, sizes, action listeners and adds them to the introducing panel.
     */
    private void initButtons() {
        buttons = new ArrayList<>();

        JButton start = new JButton();
        start.setIcon(new ImageIcon(START_BUTTON_PATH));

        JButton load = new JButton();
        load.setIcon(new ImageIcon(LOAD_BUTTON_PATH));

        JButton quit = new JButton();
        quit.setIcon(new ImageIcon(QUIT_BUTTON_PATH));

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

    /**
     * This method sets the location of the buttons on the introducing panel.
     */
    private void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation((width - BUTTON_WIDTH) / 2,
                    (height - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (int) (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT) + Y_OFFSET);
        }
    }

    /**
     * This method initializes the title label with the game logo and adds it to the introducing panel.
     */
    private void initTitleLabel() {
        ImageIcon icon = new ImageIcon(TITLE_LABEL_PATH);

        JLabel titleLabel = new JLabel(icon, JLabel.CENTER);

        titleLabel.setBounds((width - icon.getIconWidth()) / 2, 10, icon.getIconWidth(), icon.getIconHeight());

        introducingPanel.add(titleLabel);
    }

    /**
     * This method sets the background image of the introducing panel by adding a JLabel with the background image.
     */
    private void setBackgroundImage() {
        ImageIcon background = new ImageIcon(BACKGROUND_IMAGE_PATH);

        JLabel backgroundLabel = new JLabel(background);

        backgroundLabel.setBounds(0, 0, width, height);

        introducingPanel.add(backgroundLabel);

        introducingPanel.setComponentZOrder(backgroundLabel, introducingPanel.getComponentCount() - 1);
    }

    /**
     * This method initializes the introducing panel with a null layout, sets its preferred size, and adds it to the frame.
     */
    private void initIntroducingPanel() {
        introducingPanel = new JPanel(null);

        introducingPanel.setOpaque(false);

        introducingPanel.setPreferredSize(new Dimension(width, height));

        frame.add(introducingPanel, BorderLayout.CENTER);
    }

    @Override
    public String getImagePath() {
        return "resources/windowIcons/introducingWindowIcon.png";
    }

    /**
     * This method shows an error message with the specified message.
     *
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
