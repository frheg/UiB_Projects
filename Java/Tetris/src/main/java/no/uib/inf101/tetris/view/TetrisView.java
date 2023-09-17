package no.uib.inf101.tetris.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;

public class TetrisView extends JPanel {

  double OuterMargin = 15;
  private ColorTheme ColorTheme;
  double InnerMargin = 2;
  private ViewableTetrisModel model;
  private int height = 800; // Defines height for the window in pixels
  private int width = 500;  // Defines width for the window in pixels

  // Constructor
  public TetrisView(ViewableTetrisModel model) {
    this.model = model;
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(width, height));
    this.ColorTheme = new DefaultColorTheme();
    this.setBackground(ColorTheme.getBackgroundColor());
  }

  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object.
  // Note: NEVER call paintComponent directly yourself
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
  }

  private void drawGame(Graphics2D g) {   // Draws the game-components
    Rectangle2D rectangle = new Rectangle2D.Double(OuterMargin, OuterMargin, getWidth() - 2 * OuterMargin, getHeight() - 2 * OuterMargin);
    CellPositionToPixelConverter cellPositionToPixelConverter = new CellPositionToPixelConverter(rectangle, model.getDimension(), InnerMargin);
    g.setColor(this.ColorTheme.getBackgroundColor());
    g.fill(rectangle);
    drawCells(g, model.getTilesOnBoard(), cellPositionToPixelConverter, this.ColorTheme);
    if (model.getGameState() == GameState.GAME_OVER) {
      drawGameOver(g, ColorTheme, rectangle);
    } else if (model.getGameState() == GameState.GAME_PAUSED) {
      drawPauseScreen(g, ColorTheme, rectangle);
    } else if (model.getGameState() == GameState.START_GAME) {
      drawStartGame(g, ColorTheme, rectangle);
    }
    drawCells(g, model.getTetrominoOnBoard(), cellPositionToPixelConverter, ColorTheme);
    drawScore(g, ColorTheme, rectangle);
  }

  // Draws the cells in the grid
  private static void drawCells(Graphics2D canvas, Iterable<GridCell<Character>> cellCollection,
      CellPositionToPixelConverter CPTPC, ColorTheme color) {
    for (GridCell<Character> cell : cellCollection) {
      Rectangle2D rectangle = CPTPC.getBoundsForCell(cell.pos());
      Color col = color.getCellColor(cell.value());
      canvas.setColor(col);
      canvas.fill(rectangle);
    }
  }

  // Draws gameoverscreen
  private void drawGameOver(Graphics2D canvas, ColorTheme color, Rectangle2D rectangle) {
    canvas.setColor(ColorTheme.getGameOverColor());
    canvas.fill(rectangle);
    canvas.setColor(ColorTheme.getTextcolor());
    canvas.setFont(new Font("SansSerif", Font.BOLD, 50));
    Inf101Graphics.drawCenteredString(canvas, "GAME OVER!", rectangle);
    canvas.setFont(new Font("SansSerif", Font.BOLD, 20));
    canvas.drawString("Final score: " + (model.getScore()*100), 40, 730);
    canvas.drawString("Restart? Press 'R'", 40, 760);
  }

  // Draws pausescreen
  private void drawPauseScreen(Graphics2D canvas, ColorTheme color, Rectangle2D rectangle) {
    canvas.setColor(ColorTheme.getGameOverColor());
    canvas.fill(rectangle);
    canvas.setColor(ColorTheme.getTextcolor());
    canvas.setFont(new Font("SansSerif", Font.BOLD, 50));
    Inf101Graphics.drawCenteredString(canvas, "PAUSED", rectangle);
  }

  // Draws startscreen
  private void drawStartGame(Graphics2D canvas, ColorTheme color, Rectangle2D rectangle) {
    canvas.setColor(ColorTheme.getGameOverColor());
    canvas.fill(rectangle);
    canvas.setColor(ColorTheme.getTextcolor());
    canvas.setFont(new Font("SansSerif", Font.BOLD, 20));
    Inf101Graphics.drawCenteredString(canvas, "PRESS 'ENTER' TO START/PAUSE GAME", rectangle);
    canvas.drawString("Directions: 'Arrowkeys'", 40, 670);
    canvas.drawString("Drop: 'Space'", 40, 700);
    canvas.drawString("Pause: 'Enter'", 40, 730);
    canvas.drawString("Restart: 'R'", 40, 760);
  }
  
  // Draws the score
  private void drawScore(Graphics2D canvas, ColorTheme color, Rectangle2D regtangle) {
    canvas.setColor(ColorTheme.getTextcolor());
    canvas.setFont(new Font("SansSerif", Font.BOLD, 15));
    canvas.drawString("Score: " + (model.getScore()*100), 50, 50);
  }
}
