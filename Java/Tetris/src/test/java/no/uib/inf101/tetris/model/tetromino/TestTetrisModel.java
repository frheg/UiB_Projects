package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.Tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.Tetromino.Tetromino;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {
    @Test
    public void initialPositionOfO() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    }

    @Test
    public void initialPositionOfI() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
    }

    @Test
    public void testMovedTetromino() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        List<GridCell<Character>> tetroCellsMoved = new ArrayList<>();

        for (GridCell<Character> i : model.getTetrominoOnBoard()) {
            tetroCells.add(i);
        }

        model.moveTetromino(1, 1);

        for (GridCell<Character> j : model.getTetrominoOnBoard()) {
            tetroCellsMoved.add(j);
        }

        assertNotEquals(tetroCells, tetroCellsMoved);
    }

    @Test
    public void testDroppedTetromino() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        model.dropTetromino();

        assertTrue(board.get(new CellPosition(19, 3)) == 'I');
        assertTrue(board.get(new CellPosition(19, 4)) == 'I');
        assertTrue(board.get(new CellPosition(19, 5)) == 'I');
        assertTrue(board.get(new CellPosition(19, 6)) == 'I');
    }

    @Test
    public void testClockTick() {
        TetrisBoard board = new TetrisBoard(2, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        List<GridCell<Character>> tetroCellsMoved = new ArrayList<>();

        for (GridCell<Character> i : model.getTetrominoOnBoard()) {
            tetroCells.add(i);
        }

        model.clockTick();

        for (GridCell<Character> j : model.getTetrominoOnBoard()) {
            tetroCellsMoved.add(j);
        }

        assertNotEquals(tetroCells, tetroCellsMoved);

        model.clockTick();

        assertTrue(board.get(new CellPosition(1, 3)) == 'I');
        assertTrue(board.get(new CellPosition(1, 4)) == 'I');
        assertTrue(board.get(new CellPosition(1, 5)) == 'I');
        assertTrue(board.get(new CellPosition(1, 6)) == 'I');
    }

    @Test
    void testRotatedTetrominoMethod() {
        Tetromino tetro = Tetromino.newTetromino('T').rotatedCounterClockwise();
        // TetrisModel model = new TetrisModel(board, factory);

        // model.rotatedCounterClockwise();

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
