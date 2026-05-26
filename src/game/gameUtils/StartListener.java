package game.gameUtils;

/**
 * This interface is used for starting game. It also checks if game is loading or not.
 *
 * @author Maksym Kulynych
 */
public interface StartListener {
    void onStart(boolean isLoading);
}
