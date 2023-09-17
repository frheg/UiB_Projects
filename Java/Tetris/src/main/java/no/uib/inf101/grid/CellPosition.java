package no.uib.inf101.grid;

// Les om records her: https://inf101.ii.uib.no/notat/mutabilitet/#record

/**
 * A CellPosition consists of a row and a column.
 *
 * @param row  the row of the cell
 * @param col  the column of the cell
 */
public record CellPosition(int row, int col) {}

