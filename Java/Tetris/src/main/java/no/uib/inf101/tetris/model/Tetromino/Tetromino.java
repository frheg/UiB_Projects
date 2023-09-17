package no.uib.inf101.tetris.model.Tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class Tetromino implements Iterable<GridCell<Character>> { // The class for constructing a new tetromino-object.

    private char symbol;
    private boolean[][] shape;
    private CellPosition position;

    private Tetromino(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    public static Tetromino newTetromino(char type) {   // Defines the different shapes and characteristics of the tetrominos
        boolean[][] shape = switch (type) {
            case 'L' -> new boolean[][] {   
                    { false, false, false },
                    { true, true, true },
                    { true, false, false }
            };
            case 'J' -> new boolean[][] {
                    { false, false, false },
                    { true, true, true },
                    { false, false, true }
            };
            case 'S' -> new boolean[][] {
                    { false, false, false },
                    { false, true, true },
                    { true, true, false }
            };
            case 'Z' -> new boolean[][] {
                    { false, false, false },
                    { true, true, false },
                    { false, true, true }
            };
            case 'T' -> new boolean[][] {
                    { false, false, false },
                    { true, true, true },
                    { false, true, false }
            };
            case 'I' -> new boolean[][] {
                    { false, false, false, false },
                    { true, true, true, true },
                    { false, false, false, false },
                    { false, false, false, false }
            };
            case 'O' -> new boolean[][] {
                    { false, false, false, false },
                    { false, true, true, false },
                    { false, true, true, false },
                    { false, false, false, false }
            };
            default -> throw new IllegalArgumentException("Unexpected value: " + type); // If paramater is not in case, throw exeption
        };
        CellPosition position = new CellPosition(0, 0);
        Tetromino tetromino = new Tetromino(type, shape, position); // Creates new tetromino-object as the return.
        return tetromino; // return of the shape of the tetromino.
    }

    public CellPosition shifPosition(int deltaRow, int deltaCol){
        return new CellPosition(this.position.row() + deltaRow, this.position.col() + deltaCol);
    }

    public Tetromino shiftedBy(int deltaRow, int deltaCol) { // Creates a new copy of itself, but with the moved position.
        Tetromino tetromino = new Tetromino(this.symbol, this.shape, shifPosition(deltaRow, deltaCol));
        return tetromino;
    }

    public Tetromino shiftedToTopCenterOf(GridDimension gDimension) { // Creates the moved copy of itself which is sentered in the middle.
        int middlePosition = ((gDimension.cols()/2) - (shape[0].length/2)); // Center value of cols in grid
        CellPosition pos = new CellPosition(-1, middlePosition);            // The centered position in top of grid
        Tetromino tetromino = new Tetromino(this.symbol, this.shape, pos);  // A new tetromino object with the new position
        return tetromino;                                                   // Returns the tetromino
    }

    @Override
    public int hashCode() {                                                 // Hashes the position to create a unique value
        return Objects.hash(this.symbol, Arrays.deepHashCode(this.shape), this.position);
    }

    public boolean equals(Object o) {               
        // self check
        if (this == o)                              // Checks the input value is equal to the object
            return true;
        // type check and cast
        if (!(o instanceof Tetromino))              // Checks if the input is not an instanceof a type Tetromino
            return false;

        Tetromino tet = (Tetromino) o;

        boolean cond1 = this.symbol == tet.symbol;  
        boolean cond2 = Arrays.deepEquals(this.shape, tet.shape);
        boolean cond3 = Objects.equals(this.position, tet.position);

        return cond1 && cond2 && cond3;             // Returns the boolean of three boolean conditions
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {                   // Iterates through the tetromino-grid
        List<GridCell<Character>> list = new ArrayList<>();             // New list which stores the data
        for (int row = 0; row < this.shape.length; row++) {             // Iterates through the rows
            for (int col = 0; col < this.shape[row].length; col++) {    // Iterates through the columns
                if (this.shape[row][col] == true) {                     // If the value in the grid for the tetromino is true:
                    CellPosition position = new CellPosition(row + this.position.row(), col + this.position.col());     
                    GridCell<Character> value = new GridCell<Character>(position, this.symbol); // Creates a new Gridcell object with the position
                    list.add(value);                                    // Add the value to the list
                }
            }
        }
        return list.iterator();                                         // Return the list
    }

    public Tetromino rotatedCounterClockwise() {    // Rotate the tetronmino counterclockwise
        int numRows = this.shape.length;                            
        int numCols = this.shape[0].length;                         
        boolean[][] rotatedShape = new boolean[numCols][numRows];  

        // Transpose the shape
        for (int i = 0; i < numRows; i++) {             
            for (int j = 0; j < numCols; j++) {        
                rotatedShape[j][i] = this.shape[i][j];
            }
        }
        // Reverse each column to rotate counterclockwise
        for (int i = 0; i < numCols / 2; i++) {
            for (int j = 0; j < numRows; j++) {
                boolean temp = rotatedShape[i][j];
                rotatedShape[i][j] = rotatedShape[numCols - i - 1][j];
                rotatedShape[numCols - i - 1][j] = temp;
            }
        }
        // Create the new rotated tetromino
        Tetromino rotatedTetromino = new Tetromino(this.symbol, rotatedShape, this.position);
        return rotatedTetromino;
    }
}
