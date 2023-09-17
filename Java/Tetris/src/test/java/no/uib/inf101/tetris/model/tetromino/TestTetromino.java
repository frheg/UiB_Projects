package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.Tetromino.Tetromino;

public class TestTetromino {
    @Test
    public void testHashCodeAndEquals() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
        Tetromino s1 = Tetromino.newTetromino('S');
        Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

        assertEquals(t1, t2);
        assertEquals(s1, s2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1, s1);
    }
    @Test
    public void tetrominoIterationOfT() {
    // Create a standard 'T' tetromino placed at (10, 100) to test
    Tetromino tetro = Tetromino.newTetromino('T');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
        objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
    }

    @Test
    public void tetrominoIterationOfS() {
        // Create a standard 'T' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('S');
        tetro = tetro.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
    }

    @Test
    public void tetrominoDoubleMoved()  {
        Tetromino tet1 = Tetromino.newTetromino('I');   // Creates a new tetromino
        Tetromino tet2 = Tetromino.newTetromino('I');   // Creates a second tetromino
        tet1.shiftedBy(1, 1);   // Shifts the position of the first tetromine twice
        tet1.shiftedBy(1, 1);

        tet2.shiftedBy(2, 2);   // Shifts the position of the second tetromino once, but twice as far as the first one
        assertEquals(tet1, tet2);   // Checks if the positions is the same
    }

    @Test
    public void testShiftedToTopCenterOf()  {
        GridDimension grid1 = new TetrisBoard(9, 9);    // Creates a new grid with odd-numbered length 
        GridDimension grid2 = new TetrisBoard(12, 12);  // Creates a second grid with even-numbered length

        Tetromino tet1 = Tetromino.newTetromino('T');       // Creates a new Tetromino T
        Tetromino tet2 = Tetromino.newTetromino('I');       // Creates a new Tetromino I

        tet1.shiftedToTopCenterOf(grid1);                           // Shifts the first tetromino T in the center of grid1
        tet2.shiftedToTopCenterOf(grid2);                           // Shifts the second tetromino I in the center of grid2

        Tetromino tet1_1 = Tetromino.newTetromino('T');     // Creates a new Tetromino 
        Tetromino tet2_1 = Tetromino.newTetromino('I');     // Creates a new Tetromino 

        tet1_1.shiftedBy(4, 4);                 // Assigns presumed coordinates to both the new Tetrominos
        tet2_1.shiftedBy(5, 5);

        assertEquals(tet1, tet1_1);                             // Asserts if the presumed and moved positions is the same in both cases
        assertEquals(tet2, tet2_1);
    }

    @Test
    public void testRotate() {
        // Create a standard 'T' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('T').rotatedCounterClockwise();

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 1), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 1), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 2), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 1), 'T')));
    }

}
