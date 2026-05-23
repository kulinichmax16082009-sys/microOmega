package game.gameObjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.gameUtils.MyColor;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Comparable<Cell>, Serializable {
    private int value;
    private ArrayList<MyColor> possibleColors;
    private Color color;

    private final static String JSON_FILE_PATH = "resources/jsonFiles/cell.json";

    public Cell(int value) {
        this.value = value;
        initPossibleColors();
        attachColorToValue();
    }

    public Cell() {
        value = 0;
        possibleColors = new ArrayList<>();
        initPossibleColors();
        attachColorToValue();
    }

    private void initPossibleColors() {
        ObjectMapper mapper = new ObjectMapper();

        possibleColors = new ArrayList<>();

        try (InputStream input = new FileInputStream(JSON_FILE_PATH)) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void attachColorToValue() {
        int colorValue;

        if (this.value >  2048 || this.value <= 0 || this.value % 2 != 0) {
            value = 0;
            color = new Color(possibleColors.get(0).getRed(), possibleColors.get(0).getGreen(), possibleColors.get(0).getBlue());
            return;
        }

        for (int i = 1; i < possibleColors.size(); i++) {
            colorValue = (int) Math.pow(2, i);
            if (colorValue == this.value) {
                color = new Color(possibleColors.get(i).getRed(), possibleColors.get(i).getGreen(), possibleColors.get(i).getBlue());
                break;
            }
        }
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<MyColor> getPossibleColors() {
        return possibleColors;
    }

    public void setPossibleColors(ArrayList<MyColor> possibleColors) {
        this.possibleColors = possibleColors;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Value: " + value + ", color: " + color;
    }

    @Override
    public int compareTo(Cell o) {
        return Integer.compare(this.value, o.value);
    }
}
