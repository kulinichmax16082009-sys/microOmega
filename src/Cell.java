import java.awt.*;

public class Cell {
    private int value;
    private Color color;

    public Cell(int value) {
        this.value = value;
        initializeColor();
    }

    private void initializeColor() {
        switch (value) {
            case 2: color = new Color(238, 228, 218);
                break;
            case 4: color = new Color(235, 216, 182);
                break;
            case 8: color = new Color(243, 176, 120);
                break;
            case 16: color = new Color(244, 149, 98);
                break;
            case 32: color = new Color(245, 124, 95);
                break;
            case 64: color = new Color(247, 92, 59);
                break;
            case 128: color = new Color(236, 205, 114);
                break;
            case 256: color = new Color(235, 203, 95);
                break;
            case 512: color = new Color(235, 198, 78);
                break;
            case 1024: color = new Color(236, 195, 65);
                break;
            case 2048: color = new Color(237, 190, 46);
                break;
            default:
                value = 0;
                color = new Color(189, 172, 151);
                break;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Value: " + value + ", color: " + color;
    }
}
