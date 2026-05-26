package game.gameUtils;

import java.io.Serializable;

/**
 * This class represents color in game for .json files to be converted into Color objects.
 *
 * @author Maksym Kulynych
 */
public class MyColor implements Serializable {
    private int red;
    private int green;
    private int blue;

    public MyColor() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public void colorFilter() {
        if (red < 0) red = 0;
        if (red > 255) red = 255;
        if (green < 0) green = 0;
        if (green > 255) green = 255;
        if (blue < 0) blue = 0;
        if (blue > 255) blue = 255;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "Color: [red: " + red + ", green: " + green + ", blue: " + blue + "]";
    }
}
