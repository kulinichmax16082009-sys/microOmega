package game.gameUtils;

import game.gameObjects.Player;

import java.io.*;

/**
 * This class is used for saving and loading game data like player and board.
 *
 * @author Maksym Kulynych
 */
public class GameData implements Serializable {
    private Player player;
    private BoardManager boardManager;

    public GameData(Player player, BoardManager boardManager) {
        this.player = player;
        this.boardManager = boardManager;
    }

    /**
     * This method updates game data with new player and board manager.
     *
     * @param player the new player
     * @param boardManager the new board manager
     */
    public void update(Player player, BoardManager boardManager) {
        this.player = player;
        this.boardManager = boardManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * This method saves the current game data to a file specified by the file path.
     *
     * @param filePath the path of the file where the game data will be saved
     */
    public void saveGame(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();

            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This method loads game data from a file specified by the file path.
     *
     * @param filePath the path of the file from which the game data will be loaded
     * @return the loaded game data, or null if an error occurs
     */
    public static GameData loadData(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            GameData gameData = (GameData) ois.readObject();

            ois.close();
            fis.close();

            return gameData;
        } catch (Exception e) {
            return null;
        }
    }
}
