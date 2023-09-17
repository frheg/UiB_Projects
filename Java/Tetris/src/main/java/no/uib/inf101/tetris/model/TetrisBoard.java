package no.uib.inf101.tetris.model;

import java.util.function.BooleanSupplier;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid<Character>{
    private int rowsThrown = 0;

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');     // Defines a defaultvalue '-' for the cells in the grid
    }

    public String prettyString() {                  // Converts the grid into a string containing the values in the grid
        String string = "";
        for (int row = 0; row < this.rows(); row++){
            for (int col = 0; col < this.cols(); col++){
                string += get(new CellPosition(row, col));
            }
            string += "\n";
        }
        return string.strip();
    }

    public BooleanSupplier get(Object pos) {
        return null;
    }
    // Samarbeid med Synne Bondevik

    private boolean existsInRow(int rowNumber)   {   // Checks if an element exists in the board
        for (int i = 0; i < cols(); i++){
            if (get(new CellPosition(rowNumber, i)) == '-'){
                return false;
            }
        }
        return true;
    }

    private void setValueToCell(int rowNumber)    {   // Sets all the cells in a row to a given value
        for (int colNumber = 0; colNumber < cols(); colNumber++){
            set(new CellPosition(rowNumber, colNumber), '-');
        }
    }

    private void copyValue(int destination, int source) {    // Copies the value from a source to a destination
        for (int row = 0; row < cols(); row++) {
            set(new CellPosition(source, row), get(new CellPosition(destination, row)));
        }
    }

    public int removeFullRows() {   // Copies all values from one row to another
        int a = rows() - 1;     
        int b = rows() - 1;
        while (a >= 0){       
            while (b >= 0 && existsInRow(b)){       
                    rowsThrown++;
                    b--;
            }    
            if (b >= 0){                           
                copyValue(b, a);
            }  
            else {
                setValueToCell(a);
            }    
                a--;
                b--;    
        }
        return rowsThrown;
    }
    // Samarbeid med Ingvild Karlsen Sætra

    public void clearBoard() {                  // Clears the board for the restart-method
        for (int row = 0; row < rows(); row++) {
            rowsThrown = 0;
            setValueToCell(row);
        }
    }
}

