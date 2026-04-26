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
        introducingWindow.show();
    }
}
