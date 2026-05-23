package game.gameUtils;

public class MyColor {
    private int red;
    private int green;
    private int blue;

    public MyColor() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
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
