package game.gameObjects;

/**
 * This class represents a moving cell in the game, which extends the basic Cell class and includes properties for movement and size.
 *
 * @author Maksym Kulynych
 */
public class MovingCell extends Cell {
    private int size;
    private int x, y;
    private int deltaX, deltaY;

    public MovingCell(int value) {
        super(value);
        size = 0;
        x = 0;
        y = 0;
        deltaX = 0;
        deltaY = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }
}
