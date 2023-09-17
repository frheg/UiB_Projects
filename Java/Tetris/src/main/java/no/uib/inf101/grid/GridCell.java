package no.uib.inf101.grid;

// Les om records her: https://inf101.ii.uib.no/notat/mutabilitet/#record


/**
 * A CellColor contains a CellPosition and a Color.
 *
 * @param cellPosition  the position of the cell
 * @param color        the color of the cell
 */
public record GridCell<E>(CellPosition pos, E value) {}
