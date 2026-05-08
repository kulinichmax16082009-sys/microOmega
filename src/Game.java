import javax.swing.*;

public class Game implements StartListener {
    private IntroducingWindow introducingWindow;
    private GameWindow gameWindow;
    private Player player;

    public Game() {
        this.player = new Player();
    }

    public void play() {
        introducingWindow = new IntroducingWindow(this);
    }

    @Override
    public void onStart() {
        introducingWindow.close();

        gameWindow = new GameWindow();

        gameLoop();
    }

    public void gameLoop() {
        Timer timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.updateLabel(player.getScore(), player.getTime());
            gameWindow.getGamePanel().repaint();
        });

        timer.start();
    }
}
