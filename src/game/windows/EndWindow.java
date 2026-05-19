package game.windows;

import game.gameUtils.Game;
import game.gameObjects.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EndWindow extends BasicWindow {

    private JButton playAgain;
    private JButton quit;

    public EndWindow(Player player) {
        super("2048 - End Window", 350, 350);

        initStats(player);
        initButtons();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

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

    public void initButtons() {

        playAgain = new JButton("Play Again");

        quit = new JButton("Quit");

        styleButton(playAgain, new Color(143, 122, 102));
        styleButton(quit, new Color(119, 110, 101));

        playAgain.addActionListener(e -> {
            close();

            Game newGame = new Game();
            newGame.play();
        });

        quit.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));

        buttonPanel.setBackground(new Color(250, 248, 239));
        buttonPanel.setBorder(new EmptyBorder(0, 25, 25, 25));

        buttonPanel.add(playAgain);
        buttonPanel.add(quit);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setFont(new Font("Arial", Font.BOLD, 18));

        button.setPreferredSize(new Dimension(140, 50));
    }

    @Override
    public String getImagePath() {
        return "resources/windowIcons/endingWindowIcon.jpg";
    }
}