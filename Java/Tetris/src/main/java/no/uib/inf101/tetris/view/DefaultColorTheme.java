package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(char c) {
        Color color = switch(c) { 
            case '-' -> new Color(127, 127, 127);

            case 'L' -> new Color(255, 255, 0);  
            case 'J' -> new Color(0, 255, 0);
            case 'S' -> new Color(0, 255, 255);
            case 'Z' -> new Color(0, 0, 255);
            case 'T' -> new Color(255, 127, 0);
            case 'I' -> new Color(128, 0, 128);
            case 'O' -> new Color(255, 0, 0);
            case 'r' -> Color.RED;

            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
        };
        return color;
    }

    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(50, 50, 50);
    }

    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getTextcolor() {
        return new Color(255, 255, 255);
    }
}
