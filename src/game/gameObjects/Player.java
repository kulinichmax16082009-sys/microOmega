package game.gameObjects;

import java.io.Serializable;

/**
 * This class represents the player in the game, keeping track of score and time.
 *
 * @author Maksym Kulynych
 */
public class Player implements Serializable {
    private int score;
    private long time;

    public Player() {
        this.score = 0;
        this.time = 0;
    }

    /**
     * This method adds points to the player's score.
     *
     * @param points the number of points to add to the score
     */
    public void addScore(int points) {
        this.score += points;
    }

    /**
     * This method represents a timer tick for the player, which will increase the time 1 s.
     */
    public void tickTime() {
        this.time++;
    }

    public int getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", time=" + time +
                '}';
    }
}
