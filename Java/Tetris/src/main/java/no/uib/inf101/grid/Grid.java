package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E>{

    private int rows;
    private int cols;
    private List<List<E>> grid;

    // Constructor #1
    public Grid(int rows, int cols) {
        this(rows, cols, null); // Call on constructor #2
    }

    // Constructor #2
    public Grid(int i, int j, E defaultValue) { // Creates a new grid with the paramteres as a width and height aswell as a defaultvalue
        this.rows = i;
        this.cols = j;
        this.grid = new ArrayList<>();

        for (int r = 0; r < this.rows; r++)  {  // For each row
            ArrayList<E> theRow = new ArrayList<>();    // Add a new list
                for (int c = 0; c < this.cols; c++)   { // For each index in the list
                    theRow.add(defaultValue);   // Add the default value
                }
            this.grid.add(theRow);  // Add the list/rows to the grid
        } 
    }   // A two-dimensional grid is now created

    @Override
    public int rows() {
        return this.rows;   // Returns the rows.
    }

    @Override
    public int cols() {
        return this.cols;   // Returns the cols.
    }

    @Override
    public Iterator<GridCell<E>> iterator() {   // Iterates through the 2D-grid and makes a new list with the values and positions of the values stored for each value in the grid
        List<GridCell<E>> list = new ArrayList<>(); // Creates an empty list
    for (int n = 0; n < this.rows(); n++) {     // Iterates through each row
      for (int m = 0; m < this.cols(); m++) {   // Iterates through each collumn in each row
        CellPosition position = new CellPosition(n, m); // Finds the cellposition of the grid in the iteration
        E value = this.get(position);                   // Gets the value of the given position
        GridCell<E> gridCell = new GridCell<E>(position, value);    // Creates a GridCell object with both the positon and value stored
        list.add(gridCell);                             // Adds the information to the list
      }     
    }
    return list.iterator(); // Returns the list with the values
    }
    // Samarbeid med Sara Stavang og Ella Mathilde Solberg

    @Override
    public void set(CellPosition pos, E value) {    // Sets the position with a value
        if(!positionIsOnGrid(pos)){                 // Checks if position is within bounds
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        List<E> row = this.grid.get(pos.row());    
        row.set(pos.col(), value);
    }

    @Override
    public E get(CellPosition pos) {    // Gets the cellposition
        if (!positionIsOnGrid(pos)) {   // If the position is out of bounds, it throws an exeption
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        return this.grid.get(pos.row()).get(pos.col()); // Returns the position
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) { // Checks if the position is on the grid.
        if (pos.row() < 0)  return false;              
        if (pos.col() < 0)  return false;
        if (pos.row() >= this.rows())  return false;
        if (pos.col() >= this.cols())  return false;
        else return true;
    }
}