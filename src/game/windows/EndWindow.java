package game.windows;

import game.gameUtils.Game;
import game.gameObjects.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class represents the end window of the game, which is displayed when the game is over.
 * It shows the player's score and time and options to play again or quit.
 *
 * @author Maksym Kulynych
 */
public class EndWindow extends BasicWindow {
    private JButton playAgain;
    private JButton quit;

    /**
     * This constructor initializes the end window with the player's score and time, and sets up the buttons.
     *
     * @param player the player whose score and time will be displayed in the end window
     */
    public EndWindow(Player player) {
        super("2048 - End Window", 350, 350);

        initQuitButton();
        initPlayAgainButton();
        initStats(player);
        addButtonsToWindow();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method creates a panel for displaying the player's score and time, sets its background color and adds the labels to it.
     *
     * @param player the player whose score and time will be displayed in the end window
     */
    private void initStats(Player player) {
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 0, 10));

        statsPanel.setBackground(new Color(250, 248, 239));
        statsPanel.setBorder(new EmptyBorder(25, 40, 25, 40));

        JLabel scoreLabel = new JLabel("Score: " + player.getScore(), JLabel.CENTER);

        JLabel timeLabel = new JLabel("Time: " + player.getTime() + " s", JLabel.CENTER);

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        scoreLabel.setForeground(new Color(119, 110, 101));
        timeLabel.setForeground(new Color(119, 110, 101));

        statsPanel.add(scoreLabel);
        statsPanel.add(timeLabel);

        frame.add(statsPanel, BorderLayout.CENTER);
    }

    /**
     * This method creates a panel for the "GAME OVER" label, sets its background color and adds the label to it.
     */
    public void initBadEnd() {
        JPanel topPanel = new JPanel();

        topPanel.setBackground(new Color(119, 110, 101));
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("GAME OVER");

        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        topPanel.add(label);

        frame.add(topPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method creates a panel for the "YOU WIN!" label, sets its background color and adds the label to it.
     */
    public void initGoodEnd() {
        JPanel topPanel = new JPanel();

        topPanel.setBackground(new Color(237, 194, 46));
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("YOU WIN!");

        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        topPanel.add(label);

        frame.add(topPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method initializes the "Quit" button, sets its background color and adds an action listener.
     */
    private void initQuitButton() {
        quit = new JButton("Quit");
        styleButton(quit, new Color(119, 110, 101));
        quit.addActionListener(e -> System.exit(0));
        quit.setBackground(new Color(119, 110, 101));
    }

    /**
     * This method initializes the "Play Again" button, sets its background color and adds an action listener.
     */
    private void initPlayAgainButton() {
        playAgain = new JButton("Play Again");
        styleButton(playAgain, new Color(143, 122, 102));
        playAgain.addActionListener(e -> {
            close();
            Game newGame = new Game();
            newGame.play();
        });
    }

    /**
     * This method creates a panel for the buttons, sets its background color and adds buttons to it.
     */
    private void addButtonsToWindow() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));

        buttonPanel.setBackground(new Color(250, 248, 239));
        buttonPanel.setBorder(new EmptyBorder(0, 25, 25, 25));

        buttonPanel.add(playAgain);
        buttonPanel.add(quit);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * This method styles the buttons by setting their background color, foreground color, font and preferred size.
     *
     * @param button the button to be styled
     * @param color the background color to be set for the button
     */
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(new Font("Arial", Font.BOLD, 18));

        button.setPreferredSize(new Dimension(140, 50));
    }

    @Override
    public String getImagePath() {
        return "resources/windowIcons/endingWindowIcon.jpg";
    }
}