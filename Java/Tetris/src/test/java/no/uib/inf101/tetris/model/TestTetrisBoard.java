package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class TestTetrisBoard {
    @Test
    public void testPrettyString() {
        TetrisBoard board = new TetrisBoard(3, 4);
        board.set(new CellPosition(0, 0), 'g');
        board.set(new CellPosition(0, 3), 'y');
        board.set(new CellPosition(2, 0), 'r');
        board.set(new CellPosition(2, 3), 'b');
        String expected = String.join("\n", new String[] {
            "g--y",
            "----",
            "r--b"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void testRemoveFullRows() {
        // Tester at fulle rader fjernes i brettet:
        // -T
        // TT
        // LT
        // L-
        // LL

        TetrisBoard board = new TetrisBoard(5, 2);
        board.set(new CellPosition(0, 1), 'T');
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(2, 1), 'T');
        board.set(new CellPosition(4, 0), 'L');
        board.set(new CellPosition(4, 1), 'L');
        board.set(new CellPosition(3, 0), 'L');
        board.set(new CellPosition(2, 0), 'L');

        assertEquals(3, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "--",
                "--",
                "--",
                "-T",
                "L-"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void testKeepBottomRow(){
        // Tester at fulle rader fjernes i brettet:
        // -T
        // TT
        // LT
        // L-
        // -L

        TetrisBoard board = new TetrisBoard(5, 2);
        board.set(new CellPosition(0, 1), 'T');
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(2, 1), 'T');
        //board.set(new CellPosition(4, 0), 'L');
        board.set(new CellPosition(4, 1), 'L');
        board.set(new CellPosition(3, 0), 'L');
        board.set(new CellPosition(2, 0), 'L');

        assertEquals(2, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "--",
                "--",
                "-T",
                "L-",
                "-L"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void testRemoveTopRow() {
        // Tester at fulle rader fjernes i brettet:
        // -T
        // TT
        // LT
        // L-
        // -L

        TetrisBoard board = new TetrisBoard(5, 2);
        //board.set(new CellPosition(0, 1), 'T');
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(2, 1), 'T');
        board.set(new CellPosition(4, 0), 'L');
        board.set(new CellPosition(4, 1), 'L');
        board.set(new CellPosition(3, 0), 'L');
        board.set(new CellPosition(2, 0), 'L');

        assertEquals(3, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "--",
                "--",
                "--",
                "--",
                "L-"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void testRemoveFullRowsWider() {
        // Tester at fulle rader fjernes i brettet:
        // -TT
        // TT-
        // LTT
        // L-T

        TetrisBoard board = new TetrisBoard(4, 3);
        board.set(new CellPosition(0, 1), 'T');
        board.set(new CellPosition(0, 2), 'T');
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(2, 0), 'L');
        board.set(new CellPosition(2, 1), 'T');
        board.set(new CellPosition(2, 2), 'T');
        board.set(new CellPosition(3, 0), 'L');
        board.set(new CellPosition(3, 2), 'T');

        assertEquals(1, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "---",
                "-TT",
                "TT-",
                "L-T"
        });
        assertEquals(expected, board.prettyString());
    }
}
