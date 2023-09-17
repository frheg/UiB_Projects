package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class CellPositionToPixelConverter {
  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  // Constructor
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.margin = margin;
    this.gd = gd;
  }
  
  public Rectangle2D getBoundsForCell(CellPosition cp) {    // Checks the bounds for a cell
   double cellWidth = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
   double cellHeight = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
   double cellX = box.getX() + margin + (cellWidth + margin) * cp.col();
   double cellY = box.getY() + margin + (cellHeight + margin) * cp.row();
   return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
  }
}
