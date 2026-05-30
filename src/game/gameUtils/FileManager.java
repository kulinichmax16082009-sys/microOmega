package game.gameUtils;

import game.gameObjects.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class manages file operations related to writing or reading.
 *
 * @author Maksym Kulynych
 */
public class FileManager {

    /**
     * This method saves player's stats into .txt file.
     *
     * @param player player, whose stats will be saved
     * @param filePath path to the .txt file, where stats will be saved
     * @param isWin boolean value, that shows if player won the game or not
     */
    public void saveStats(Player player, String filePath, boolean isWin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            LocalTime localTime = LocalTime.now();
            LocalDate localDate = LocalDate.now();
            bw.write("Date: " + localDate + "\n");
            bw.write("Time: " + localTime + "\n");
            bw.write("----------------------------------------------------\n");
            bw.write("<Player Stats>\n");
            bw.write("Score: " + player.getScore() + " points\n");
            bw.write("Time played: " + player.getTime() + " s\n");
            bw.write("Game result: " + (isWin ? "Player Won!" : "Game Over!") + "\n");
            bw.write("====================================================\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
