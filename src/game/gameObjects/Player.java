package game.gameObjects;

public class Player {
    private int score;
    private long time;

    public Player() {
        this.score = 0;
        this.time = 0;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void tickTime() {
        this.time++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", time=" + time +
                '}';
    }
}
