package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {
    /**
     * 
     * @param character
     * @return The color for the given character in the grid
     */
    public Color getCellColor(char character);

    /** Returns the color for the frame */
    public Color getFrameColor();

    /** Returns the backgroundcolor */
    public Color getBackgroundColor();

    /** Returns the gameover-color */
    public Color getGameOverColor();

    /** Returns the textcolor */
    public Color getTextcolor();
}
