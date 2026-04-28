public class Game {
    private IntroducingWindow introducingWindow;
    private GameWindow gameWindow;
    private EndWindow endWindow;

    public Game() {
        endWindow = new EndWindow(null);
        gameWindow = new GameWindow(endWindow);
        introducingWindow = new IntroducingWindow(gameWindow);
    }

    public void play() {
        Player player = new Player();

        introducingWindow.show();

        while (!introducingWindow.isGameStarted()) {
            System.out.println("Waiting for the game to start...");
        }

        player.startTimer();

        while (true) {
            gameWindow.updateLabel(player.getScore(), player.getTime());
        }
    }
}
