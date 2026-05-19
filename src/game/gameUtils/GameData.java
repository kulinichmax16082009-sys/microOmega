package game.gameUtils;

import game.gameObjects.Player;

import java.io.*;

public class GameData implements Serializable {
    private Player player;
    private BoardManager boardManager;

    public GameData(Player player, BoardManager boardManager) {
        this.player = player;
        this.boardManager = boardManager;
    }

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

    public static GameData loadGame(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            GameData gameData = (GameData) ois.readObject();

            ois.close();
            fis.close();

            return gameData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
